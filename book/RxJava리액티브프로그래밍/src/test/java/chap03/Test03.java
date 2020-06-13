package chap03;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Flow;
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

}
