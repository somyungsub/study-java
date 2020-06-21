package chap06;

import common.DebugSubscriber;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class Test06 {
  @Test
  @DisplayName("doOnNext")
  public void ex6_2() throws Exception{
    Flowable.range(1, 5)
        .doOnNext(data -> System.out.println("--- 기존 데이터 : " + data))
        .filter(data -> data % 2 == 0)
        .doOnNext(data -> System.out.println("--- filter 적용 후 데이터 : " + data))
        .subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("doOnComplete")
  public void ex6_4() throws Exception{

    Flowable.range(1, 5)
        .doOnComplete(() -> System.out.println("doOnComplete"))
        .subscribe(new DebugSubscriber<>());
  }
  
  @Test
  @DisplayName("doOnError")
  public void ex6_6() throws Exception{
    Flowable.range(1, 5)
        .doOnError(error -> System.out.println("기존 데이터:" + error.getMessage()))
        .map(data -> {
          if (data == 3) {
            throw new Exception("예외발생!!!");
          }
          return data;
        })
        .doOnError(error -> System.out.println("-- map 적용 후 : " + error.getMessage()))
        .subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("doOnSubscribe")
  public void ex6_8() throws Exception{
    Flowable.range(1, 5)
        .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
        .subscribe(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription subscription) {
            System.out.println("--- Subscriber: onSubscribe");
            subscription.request(Long.MAX_VALUE);
          }

          @Override
          public void onNext(Integer integer) {
            System.out.println("--- subscriber: onNext: " + integer);
          }

          @Override
          public void onError(Throwable throwable) {

          }

          @Override
          public void onComplete() {

          }
        });

  }

}
