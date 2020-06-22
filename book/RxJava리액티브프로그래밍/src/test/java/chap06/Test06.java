package chap06;

import common.DebugSubscriber;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

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
  
  @Test
  @DisplayName("doOnRequest")
  public void ex6_10() throws Exception{
    Flowable.range(1, 3)
        .doOnRequest(size -> System.out.println("기존 데이터 : size = " + size))
        .observeOn(Schedulers.computation())
        .doOnRequest(size -> System.out.println("--- observeOn 적용 후 : size = " + size))
        .subscribe(new Subscriber<Integer>() {

          private Subscription subscription;

          @Override
          public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);
          }

          @Override
          public void onNext(Integer integer) {
            System.out.println(integer);
            subscription.request(1);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("error ---> " + throwable);
          }

          @Override
          public void onComplete() {
            System.out.println("완료!!!");
          }
        });
  }

  @Test
  @DisplayName("doOnCancel")
  public void ex6_12() throws Exception{
    Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .doOnCancel(() -> System.out.println("doOnCancel"))
        .subscribe(new Subscriber<Long>() {

          private long startTime;
          private Subscription subscription;

          @Override
          public void onSubscribe(Subscription subscription) {
            this.startTime = System.currentTimeMillis();
            this.subscription = subscription;
            this.subscription.request(Long.MAX_VALUE);
          }

          @Override
          public void onNext(Long aLong) {
            if (System.currentTimeMillis() - startTime > 1000L) {
              System.out.println("구독해지!!");
              subscription.cancel();
              return;
            }
            System.out.println(aLong);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("error ==> " + throwable);
          }

          @Override
          public void onComplete() {
            System.out.println("완료!!!");
          }
        });

    Thread.sleep(1500L);
  }

  @Test
  @DisplayName("blockingFirst")
  public void ex6_13() throws Exception{
    Long actual = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .blockingFirst();
    assertEquals(0L, actual);
  }

  @Test
  @DisplayName("blockingLast")
  public void ex6_14() throws Exception {
    Long actual = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(3)
        .blockingLast();
    assertEquals(2L, actual);
  }
  
  @Test
  @DisplayName("blockingIterable")
  public void ex6_15() throws Exception{
    Iterable<Long> result = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(5)
        .blockingIterable();

    Iterator<Long> iterator = result.iterator();
    assertTrue(iterator.hasNext());

    assertEquals(0L, iterator.next());
    assertEquals(1L, iterator.next());
    assertEquals(2L, iterator.next());

    Thread.sleep(1000L);

    assertEquals(3L, iterator.next());
    assertEquals(4L, iterator.next());

    assertFalse(iterator.hasNext());

  }

  @Test
  @DisplayName("blockingSubscribe")
  public void ex6_17(){
    Flowable<Long> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .take(5);

    Counter counter = new Counter();

    flowable.blockingSubscribe(new DisposableSubscriber<Long>() {
      @Override
      public void onNext(Long aLong) {
        counter.increment();
      }

      @Override
      public void onError(Throwable throwable) {
        fail(throwable.getMessage());
      }

      @Override
      public void onComplete() {
        System.out.println("완료!!");
      }
    });
    assertEquals(5, counter.get());
  }

  @Test
  @DisplayName("TestSubscriber")
  public void ex6_18() throws Exception{

    Flowable<Long> target = Flowable.interval(100L, TimeUnit.MILLISECONDS);

    TestSubscriber<Long> testSubscriber = target.test();

    testSubscriber.assertEmpty();

    testSubscriber.await(150L, TimeUnit.MILLISECONDS);
    testSubscriber.assertValues(0L);

    testSubscriber.await(100L, TimeUnit.MILLISECONDS);
    testSubscriber.assertValues(0L, 1L);

  }
}
