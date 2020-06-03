package chap01;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class MainFlowable {
  public static void main(String[] args) throws InterruptedException {
//    executeExampleBasic();
//    executeSubscribeCancel();
//    requestMethodSample();
    disposableExample();
  }

  private static void executeExampleBasic() throws InterruptedException {
    // 생산자 정의 -> 생산하기
    Flowable<String> flowable = Flowable.create(emitter -> {
      String[] datas = {"Hello, World!", "안녕, RxJava!", "하나더추가"};
      for (String data : datas) {

        // 구독해지 -> 처리 중단
        if (emitter.isCancelled()) {
          System.out.println(" 구독해지 !");
          return;
        }

        // data 통지
        emitter.onNext(data);
      }

      // 완료 통지
      emitter.onComplete();
    }, BackpressureStrategy.BUFFER);// 초과한 데이터는 버퍼링

    // 처리정의 -> 처리하기
    flowable
        .observeOn(Schedulers.computation())      // 처리를 개별 스레드에서 실행
        .subscribe(new Subscriber<>() {     // 구독

          private Subscription subscription;

          @Override
          public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
//            this.subscription.request(1L);
            this.subscription.request(Long.MAX_VALUE);  // 개수 제한 없이 통지
          }

          @Override
          public void onNext(String data) {
            String threadName = Thread.currentThread().getName(); // 현재 쓰레드 이름
            System.out.println(threadName + " : " + data);        // 데이터 처리 (여기서는 간단히 출력)

            // 처리가 끝나고, 다음 1개를 요청하여 수신 대기
//            this.subscription.request(1L);
          }

          @Override
          public void onError(Throwable throwable) {
            throwable.printStackTrace();  // 에러처리
          }

          @Override
          public void onComplete() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " : 완료 ");
          }
        });

    // 잠시 대기
    Thread.sleep(500L);
  }

  public static void executeSubscribeCancel() throws InterruptedException {
    Flowable.interval(200L, TimeUnit.MILLISECONDS)
        .subscribe(new Subscriber<Long>() {

          private long startTime;
          private Subscription subscription;

          @Override
          public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            this.startTime = System.currentTimeMillis();
            this.subscription.request(Long.MAX_VALUE);
          }

          @Override
          public void onNext(Long data) {
            // 구독 시작부터 500밀리초가 지나면 구독을 해지 -> 처리중단
            if ((System.currentTimeMillis() - startTime) > 1000) {
              subscription.cancel();
              System.out.println("구독해지!");
              return;
            }
            System.out.println("data = " + data);
          }

          @Override
          public void onError(Throwable throwable) {

          }

          @Override
          public void onComplete() {

          }
        });

    Thread.sleep(2000L);
  }

  public static void requestMethodSample() {
    Flowable.just(1, 2, 3, 4, 5, 6, 7)
        .subscribe(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription subscription) {
            System.out.println("onSubscribe start!");
            subscription.request(Long.MAX_VALUE);
            System.out.println("onSubscribe end!!");
          }

          @Override
          public void onNext(Integer integer) {
            System.out.println("integer = " + integer);
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
  }

  public static void disposableExample() throws InterruptedException {
    Flowable<Long> flowable = Flowable.interval(200L, TimeUnit.MILLISECONDS);

    Disposable disposable =
        flowable.subscribe(
            data -> System.out.println("data = " + data),
            Throwable::printStackTrace,
            () -> System.out.println("완료!!")
        );
    Thread.sleep(500L);
    disposable.dispose();

  }

}
