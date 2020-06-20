package chap05;

import common.DebugSubscriber;
import io.reactivex.processors.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Test05 {
  @Test
  @DisplayName("processor가 데이터를 받아 통지")
  public void ex5_1() {
    PublishProcessor<Integer> processor = PublishProcessor.create();
    processor.subscribe(new Subscriber<>() {
      @Override
      public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer data) {
        System.out.println(data);
      }

      @Override
      public void onError(Throwable throwable) {

      }

      @Override
      public void onComplete() {

      }
    });

    processor.onNext(1);
    processor.onNext(2);
    processor.onNext(3);
  }

  @Test
  @DisplayName("PublishProcessor")
  public void ex5_2() {
    PublishProcessor<Integer> processor = PublishProcessor.create();

    processor.subscribe(new DebugSubscriber<>("No. 1"));
    processor.onNext(1);
    processor.onNext(2);
    processor.onNext(3);

    System.out.println("Subscriber No.2 추가");
    processor.subscribe(new DebugSubscriber<>("---- No. 2"));
    processor.onNext(4);
    processor.onNext(5);

    processor.onComplete();

    System.out.println("Subscriber No.3 추가");
    processor.subscribe(new DebugSubscriber<>("------ No. 3"));
  }

  @Test
  @DisplayName("BehaviorProcessor")
  public void ex5_3() {
    BehaviorProcessor<Integer> processor = BehaviorProcessor.create();

    processor.subscribe(new DebugSubscriber<>("No.1"));
    processor.onNext(1);
    processor.onNext(2);
    processor.onNext(3);

    System.out.println("Subscriber No.2 추가");
    processor.subscribe(new DebugSubscriber<>("=== No.2"));
    processor.onNext(4);
    processor.onNext(5);

    processor.onComplete();

    System.out.println("Subscriber No.3 추가");
    processor.subscribe(new DebugSubscriber<>("====== No.3"));
  }

  @Test
  @DisplayName("ReplayProcessor -> 텔레그램 채팅방 초대했을때 ? 히스토리가 보이는 방식?")
  public void ex5_4() {

    System.out.println("메모리 버퍼의 사용량 오버헤드 조심");
    ReplayProcessor<Integer> processor = ReplayProcessor.create();

    processor.subscribe(new DebugSubscriber<>("No.1"));
    processor.onNext(1);
    processor.onNext(2);
    processor.onNext(3);

    System.out.println("Subscriber No.2 추가");
    processor.subscribe(new DebugSubscriber<>("---- No.2"));

    processor.onNext(4);
    processor.onNext(5);

    System.out.println("Subscriber No.3 추가");
    processor.subscribe(new DebugSubscriber<>("------ No. 3"));

  }

  @Test
  @DisplayName("AsyncProcessor")
  public void ex5_5()  {
    AsyncProcessor<Object> processor = AsyncProcessor.create();

    processor.subscribe(new DebugSubscriber<>("No.1"));
    processor.onNext(1);
    processor.onNext(2);
    processor.onNext(3);

    System.out.println("Subscriber No.2 추가");
    processor.subscribe(new DebugSubscriber<>("---- No.2"));
    processor.onNext(4);
    processor.onNext(5);

    processor.onComplete();

    System.out.println("Subscriber No.3 추가");
    processor.subscribe(new DebugSubscriber<>("------ No. 3"));

  }

  @Test
  @DisplayName("UnicastProcessor")
  public void ex5_7() throws Exception {
    UnicastProcessor<Object> processor = UnicastProcessor.create();

    processor.onNext(1);
    processor.onNext(2);

    System.out.println("Subscriber No.1 추가");
    processor.subscribe(new DebugSubscriber<>("No.1"));

    System.out.println("Subscriber No.2 추가");
    processor.subscribe(new DebugSubscriber<>("---- No.2"));  // 에러!

    processor.onNext(3);
    processor.onComplete();
  }
}
