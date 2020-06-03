package chap01;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MainExample {
  public static void main(String[] args) throws InterruptedException {

    // 생산자 정의 -> 생산하기
    Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
      @Override
      public void subscribe(FlowableEmitter<String> emitter) throws Exception {
        String[] datas = {"Hello, World!", "안녕, RxJava!", "하나더추가"};
        for (String data : datas) {

          // 구독해지 -> 처리 중단
          if (emitter.isCancelled()) {
            return;
          }

          // data 통지
          emitter.onNext(data);
        }

        // 완료 통지
        emitter.onComplete();
      }
    }, BackpressureStrategy.BUFFER);// 초과한 데이터는 버퍼링

    // 처리정의 -> 처리하기
    flowable
        .observeOn(Schedulers.computation())      // 처리를 개별 스레드에서 실행
        .subscribe(new Subscriber<>() {     // 구독

          private Subscription subscription;

          @Override
          public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1L);
          }

          @Override
          public void onNext(String data) {
            String threadName = Thread.currentThread().getName(); // 현재 쓰레드 이름
            System.out.println(threadName + " : " + data);        // 데이터 처리 (여기서는 간단히 출력)

            // 처리가 끝나고, 다음 1개를 요청하여 수신 대기
            this.subscription.request(1L);
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

}
