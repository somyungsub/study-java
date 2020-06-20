package chap05;

import common.DebugSubscriber;
import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class Test05 {
  @Test
  @DisplayName("processor가 데이터를 받아 통지")
  public void ex5_1() throws Exception{
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
  public void ex5_2() throws Exception{
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
}
