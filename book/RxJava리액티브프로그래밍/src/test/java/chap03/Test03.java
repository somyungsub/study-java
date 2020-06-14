package chap03;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.ResourceSubscriber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test03 {
  @Test
  @DisplayName("이터레이터 패턴사용")
  public void ex3_1() {
    List<String> list = List.of("a", "b", "c");
    Iterator<String> iterator = list.iterator();

    while (iterator.hasNext()) {
      String value = iterator.next();
      System.out.println("value = " + value);
    }
  }

  @Test
  @DisplayName("Flowable 사용")
  public void ex3_3() {
    List<String> list = List.of("a", "b", "C");
    Flowable<String> flowable = Flowable.fromIterable(list);
    flowable.subscribe(o -> System.out.println("o = " + o));
  }

  @Test
  @DisplayName("데이터를 받는 측이 무거운 처리 작업")
  public void ex3_4() throws InterruptedException {
    /*
      생산속도 > 처리속도
       - 생산이 빠르더라도, 처리속도 2초(무거운작업)에 맡게 처리가 이루어짐
       - 오버헤드 제어가 가능
     */
    Flowable.interval(1000L, TimeUnit.MILLISECONDS) // 생산 1초
        .doOnNext(data -> System.out.println("emit = " + System.currentTimeMillis() + " 밀리초: " + data))
        .subscribe(data -> Thread.sleep(2000L)) // 무거운 처리 작업 가정 (2초)
    ;

    Thread.sleep(5000L);
  }

  @Test
  @DisplayName("interval 메서드로 생성한 flowable")
  public void ex3_5() throws InterruptedException {
    /*
      생산 속도 < 처리속도
       - 그럼에도 불구하고, 1초 간격으로 처리됨
       - 생산속도에 영향을 미치지 않
     */
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .doOnNext(data -> System.out.println("emit : " + System.currentTimeMillis() + " 밀리초 : " + data))
        .subscribe(data -> Thread.sleep(500L));

    Thread.sleep(3000L);
  }

  @Test
  @DisplayName("메인스레드에서 처리작업을 하는 Flowable")
  public void ex3_6() {
    System.out.println("Start!!");

    Flowable.just(1, 2, 3)
        .subscribe(new ResourceSubscriber<Integer>() {
          @Override
          public void onNext(Integer integer) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + integer);
          }

          @Override
          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          @Override
          public void onComplete() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : 완료");
          }
        });

    System.out.println("End!!");
  }

  @Test
  @DisplayName("메인스레드가 아닌 스레드에서 처리작업을 하는 Flowable")
  public void ex3_7() throws InterruptedException {
    System.out.println("start");

    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .subscribe(new ResourceSubscriber<Long>() {
          @Override
          public void onNext(Long data) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + data);
          }

          @Override
          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          @Override
          public void onComplete() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : 완료");
          }
        });

    System.out.println("end");
    Thread.sleep(1000L);
  }

  @Test
  @DisplayName("유효한 스케쥴러")
  public void ex3_8() throws InterruptedException {
    Flowable.just(1, 2, 3, 4, 5)
        .subscribeOn(Schedulers.computation())  // RxComputationThreadPool - 연산처리 관련 스레드로 생성 (처음만 적용)
        .subscribeOn(Schedulers.io())           // RxCachedThreadScheduler - io 작업 관련 스레드로 생성 (이후부터 무시)
        .subscribeOn(Schedulers.single())       // RxSingleScheduler- 무시
        .subscribe(data -> {
          String name = Thread.currentThread().getName();
          System.out.println(name + " : " + data);
        });

    Thread.sleep(1000L);
  }

  @Test
  @DisplayName("observeOn 메서드로 bufferSize를 지정")
  public void ex3_9() throws InterruptedException {

    // 1. 300밀리초마다 데이터 통지 (0,1,2,...)
    Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .onBackpressureDrop();

    // 비동기로 데이터를 받게하고, 버퍼 사이즈 1로 설정
    int bufferSize = 2;
    flowable
        .observeOn(Schedulers.computation(), false, bufferSize)
        .subscribe(new ResourceSubscriber<Long>() {
          @Override
          public void onNext(Long data) {
            // 무거운 작업 가정
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + data);
          }

          @Override
          public void onError(Throwable throwable) {
            throwable.printStackTrace();
          }

          @Override
          public void onComplete() {
            System.out.println("완료!!");
          }
        });

    Thread.sleep(7000L);
  }

  @Test
  @DisplayName("flatMap 메서드 내에세 서로 다른 스레드 작동하는 Flowable")
  public void ex3_10() throws Exception {

//    Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E", "F", "G", "1", "2", "3")
    Flowable<Object> flowable = Flowable.range(1, 10000)
        .flatMap(data -> Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS)); // 1초 늦게 통지

    flowable.subscribe(data -> {
      String name = Thread.currentThread().getName();
      System.out.println(name + " : " + data);
    });

    Thread.sleep(10000L);
  }

  @Test
  @DisplayName("concatMap 메서드 내에서 서로 다른 스레드로 작동하는 Flowable 생성")
  public void ex3_11() throws InterruptedException {
    Flowable<String> flowable = Flowable.just("A", "B", "C")
        .concatMap(data -> Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS));

    flowable.subscribe(data -> {
      String name = Thread.currentThread().getName();
      String time = LocalTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));
      System.out.println(name + " : data=" + data + ", time" + time);
    });

    Thread.sleep(4000L);
  }

  @Test
  @DisplayName("concatMapEager 메서드 내에서 서로 다른 스레드로 작동하는 Flowable을 생성")
  public void ex3_12() throws Exception {
    Flowable<String> flowable = Flowable.just("A", "B", "C")
        .concatMapEager(data -> {
          return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS);
        });

    flowable.subscribe(data -> {
      String name = Thread.currentThread().getName();
      String time = LocalTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));
      System.out.println(name + ":data=" + data + ", time" + time);
    });

    Thread.sleep(2000L);

  }

  @Test
  @DisplayName("두 스레드에서 같은 객체의 변경작업을 수행")
  public void ex3_13() throws Exception {

    final Counter counter = new Counter();

    Flowable.range(1, 10_000)
        .subscribeOn(Schedulers.computation())  // 생산 스레드
        .observeOn(Schedulers.computation())    // 소비 스레드
        .subscribe(
            data -> counter.increment(),
            error -> System.out.println("error = " + error),
            () -> System.out.println("counter.get = " + counter.get())
        );

    Flowable.range(1, 10_000)
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation())
        .subscribe(
            data -> counter.increment(),
            error -> System.out.println("error = " + error),
            () -> System.out.println("counter.get() = " + counter.get())
        );

    Thread.sleep(1000L);
  }

  @Test
  @DisplayName("두 스레드에서 같은 객체의 변경작업을 수행")
  public void ex3_13_atomic버전() throws Exception {

    final AtomicCounter counter = new AtomicCounter();

    Flowable.range(1, 10_000)
        .subscribeOn(Schedulers.computation())  // 생산 스레드
        .observeOn(Schedulers.computation())    // 소비 스레드
        .subscribe(
            data -> counter.increment(),
            error -> System.out.println("error = " + error),
            () -> System.out.println("counter.get = " + counter.get())
        );

    Flowable.range(1, 10_000)
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation())
        .subscribe(
            data -> counter.increment(),
            error -> System.out.println("error = " + error),
            () -> System.out.println("counter.get() = " + counter.get())
        );

    Thread.sleep(1000L);
  }

  @Test
  @DisplayName("merge 메서드로 결합하는 예제")
  public void ex3_15() throws Exception {

    final Counter counter = new Counter();

    // counter.increment -> 10_000번호출
    Flowable<Integer> source1 = Flowable.range(1, 10_000)
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation());

    // counter.increment -> 10_000번호출 (다른 스레드에서 동시 실행)
    Flowable<Integer> source2 = Flowable.range(1, 10_000)
        .subscribeOn(Schedulers.computation())
        .observeOn(Schedulers.computation());

    // 두 Flowable을 합침
    Flowable.merge(source1, source2)
        .subscribe(
            data -> counter.increment(),
            error -> System.out.println("error = " + error),
            () -> System.out.println("counter.get() = " + counter.get())
        );

    Thread.sleep(1000L);
  }

  @Test
  @DisplayName("재시도하는 예제")
  public void ex3_16() throws Exception {
    Flowable<Integer> flowable = Flowable.<Integer>create(emitter -> {

      // Flowable 처리 시작
      System.out.println("Flowable 처리 시작");

      // 통지 처리
      for (int i = 1; i <= 4; i++) {
        if (i == 2) {
          throw new Exception("예외발생!!");
        }
        emitter.onNext(i);  // 데이터 통지
      }

      // 완료
      emitter.onComplete();
      System.out.println("Flowable 처리 완료");
    }, BackpressureStrategy.BUFFER)
        .doOnSubscribe(
            subscription -> System.out.println("flowable: doOnSubscribe")
        )
        .retry(2);

    flowable.subscribe(new Subscriber<Integer>() {
      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("subscriber: onSubscribe!!");
        subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer data) {
        System.out.println("data = " + data);
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("에러 = " + throwable);
      }

      @Override
      public void onComplete() {
        System.out.println("종료!!");
      }
    });

  }

  @Test
  @DisplayName("에러가 발생하면 대체데이터를 통지")
  public void ex3_17() throws Exception {
    Flowable.just(1, 3, 5, 0, 2, 4)
        .map(data -> 100 / data)
        .onErrorReturnItem(0)   // 대체 데이터
        .subscribe(new DisposableSubscriber<Integer>() {
          @Override
          public void onNext(Integer data) {
            System.out.println("data = " + data);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("throwable = " + throwable);
          }

          @Override
          public void onComplete() {
            System.out.println("완료!!");
          }
        });

  }

  @Test
  @DisplayName("using")
  public void ex3_18() throws Exception {
    Flowable.using(
        () -> {
          String sql = "";
          String username = "root";
          String password = "root";
          String url = "jdbc:mysql://localhost:3306/test";
          Connection connection = DriverManager.getConnection(url, username, password);
          System.out.println("connection = " + connection);
          return connection;
        },
        d -> {
          Flowable<Connection> flowable = Flowable.just(d);
          flowable.subscribe(new Subscriber<Connection>() {
            @Override
            public void onSubscribe(Subscription subscription) {
              System.out.println("onSubscribe!!");
              subscription.request(1);
            }

            @Override
            public void onNext(Connection connection) {
              try {
                String sql = "select * from test";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                  System.out.println("===Start===");
                  System.out.println(resultSet.getObject(0));
                  System.out.println(resultSet.getObject(1));
                  System.out.println(resultSet.getObject(2));
                  System.out.println("===End===");
                }
              } catch (SQLException throwables) {
                throwables.printStackTrace();
              }
            }

            @Override
            public void onError(Throwable throwable) {
              System.out.println("throwable = " + throwable);
            }

            @Override
            public void onComplete() {
              System.out.println("완료!!");
            }
          });

          return flowable;
        },
        d -> {
          System.out.println("컨슈머 자원해제!!");
          d.close();
        }
    );
  }

  @Test
  @DisplayName("MissingBackpressureException 발생")
  public void ex3_22() throws Exception {
    Flowable<Long> flowable = Flowable.interval(10L, TimeUnit.MICROSECONDS)
        .doOnNext(value -> System.out.println("emit = " + value));// 통지시 정보출력

    flowable
        .observeOn(Schedulers.computation()) // 각스레드에서 데이터 받음
        .subscribe(new Subscriber<Long>() {
          @Override
          public void onSubscribe(Subscription subscription) {
            subscription.request(Long.MAX_VALUE);
          }

          @Override
          public void onNext(Long value) {
            // 1000밀리초 기다린뒤 처리
            System.out.println("waiting......");
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("received: " + value);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("throwable = " + throwable);
          }

          @Override
          public void onComplete() {
            System.out.println("종료!!");
          }
        });
    Thread.sleep(5000L);
  }

  @Test
  @DisplayName("배압전략 - buffer")
  public void ex3_22_buffer() throws Exception {
    Flowable.interval(5L, TimeUnit.MILLISECONDS)
        .onBackpressureBuffer()
        .subscribe(new Subscriber<Long>() {

          Subscription subscription;

          @Override
          public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            subscription.request(10);
          }

          @Override
          public void onNext(Long data) {
            System.out.println("data = " + data);
            subscription.request(10);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("throwable = " + throwable);
          }

          @Override
          public void onComplete() {
            System.out.println("완료!!");
          }
        });

    Thread.sleep(5000L);

  }

  @Test
  @DisplayName("배압전략 - buffer2")
  public void ex3_22_buffer2() throws Exception {
//    Flowable.create(emitter -> {
//      int i = 0;
//      while (true) {
//        Thread.sleep(10L);
//        emitter.onNext(i++);
//      }
//    }, BackpressureStrategy.BUFFER)
//        .doOnSubscribe(subscription -> subscription.request(10))
//        .doOnNext(o -> System.out.println("o = " + o))
//        .doOnComplete(() -> System.out.println("완료!!"))
//        .doOnError(throwable -> System.out.println("throwable = " + throwable))
//        ;

    Flowable.create(emitter -> {
      int i = 0;
      while (true) {
        Thread.sleep(10L);
        emitter.onNext(i++);
      }
    }, BackpressureStrategy.BUFFER)
        .subscribe(new Subscriber<Object>() {

          private Subscription subscription;

          @Override
          public void onSubscribe(Subscription subscription) {
            System.out.println("subscription!!");
            this.subscription = subscription;
            subscription.request(10);
          }

          @Override
          public void onNext(Object o) {
            System.out.println("o = " + o);
            subscription.request(10);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("throwable = " + throwable);
          }

          @Override
          public void onComplete() {
            System.out.println("완료!!");
          }
        });

    Thread.sleep(500L);

  }

  @Test
  @DisplayName("배압 - drop")
  public void ex3_22_drop() throws Exception {
    Flowable.interval(1L, TimeUnit.MILLISECONDS)
        .onBackpressureDrop(data -> System.out.println("drop : " + data))
        .observeOn(Schedulers.computation())
        .subscribe(data -> {
          Thread.sleep(5L);
          System.out.println("data = " + data);
        });

    Thread.sleep(1000L);
  }


}
