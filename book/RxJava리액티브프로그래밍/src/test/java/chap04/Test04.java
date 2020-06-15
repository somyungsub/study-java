package chap04;

import io.reactivex.Flowable;
import io.reactivex.Single;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Test04 {

  @Test
  @DisplayName("just예제")
  public void ex4_2() {
    Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E");
    flowable.subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("fromArray")
  public void ex4_3() {
    String[] sArr = {"A", "B", "C", "D", "E"};
    Flowable<String> flowable = Flowable.fromArray(sArr);
    flowable.subscribe(new DebugSubscriber<>());

    System.out.println("==========================");

    List<String> list = List.of("A", "B", "C", "D", "E");
    Flowable<String> flowable2 = Flowable.fromIterable(list);
    flowable2.subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("fromCallable")
  public void ex4_5() {
    Flowable<Long> flowable = Flowable.fromCallable(() -> System.currentTimeMillis());
    flowable.subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("range")
  public void ex4_6() {
    Flowable
        .range(1, 5)
        .subscribe(new DebugSubscriber<>());

    Flowable
        .rangeLong(1L, 5)
        .subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("interval")
  public void ex4_7() throws InterruptedException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

    Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS);

    System.out.println("시작시각 : " + LocalDateTime.now().format(formatter));

    flowable.subscribe(data -> {
      String name = Thread.currentThread().getName();
      String time = LocalDateTime.now().format(formatter);
      System.out.println(name + ": " + time + ": data=" + data);
    });

    Thread.sleep(5000L);
  }

  @Test
  @DisplayName("timer")
  public void ex4_8() throws Exception {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

    System.out.println("시작시각 : " + LocalDateTime.now().format(formatter));

    Flowable<Long> flowable = Flowable.timer(1000L, TimeUnit.MILLISECONDS);

    flowable.subscribe(data -> {
          String name = Thread.currentThread().getName();
          String time = LocalDateTime.now().format(formatter);
          System.out.println(name + ": " + time + ": date=" + data);
        },
        throwable -> System.out.println("throwable = " + throwable),
        () -> System.out.println("완료!!")
    );

    Thread.sleep(1500L);
  }

  @Test
  @DisplayName("defer")
  public void ex4_10() throws Exception {
    Flowable<LocalDateTime> flowable = Flowable.defer(() -> Flowable.just(LocalDateTime.now()));

    flowable.subscribe(new DebugSubscriber<>("No. 1"));
    Thread.sleep(2000L);
    flowable.subscribe(new DebugSubscriber<>("No. 2"));
  }

  @Test
  @DisplayName("empty")
  public void ex4_11() {
    Flowable
        .empty()
        .subscribe(new DebugSubscriber<>());

    System.out.println("=========================");

    Flowable
        .just("A", "B", "C", "D", "", "E", "F")
        .flatMap(string -> {
          if (string.isBlank()) {
            System.out.println("빈값");
            return Flowable.empty();
          }
          return Flowable.just(string.concat("^^"));
        })
        .subscribe(new DebugSubscriber<>());

  }

  @Test
  @DisplayName("never")
  public void ex4_13() {
    Flowable
        .never()
        .subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("map")
  public void ex4_16() {
    Flowable<String> flowable = Flowable.just("A", "B", "C", "D", "E")
        .map(data -> data.toLowerCase());

    flowable.subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("flatMap")
  public void ex4_18() {
    Flowable<String> flowable = Flowable.just("A", "", "B", "", "C")
        .flatMap(data -> {
          if ("".equals(data)) {
            return Flowable.empty();
          } else {
            return Flowable.just(data.toLowerCase());
          }
        });

    flowable.subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("flatMap - mapper, combiner")
  public void ex4_20() throws InterruptedException {
    Flowable<String> flowable = Flowable.range(1, 3)
        .flatMap(data -> {
          return Flowable.interval(100L, TimeUnit.MILLISECONDS)
              .take(3);
        }, (sourceData, newData) -> "[" + sourceData + "]" + newData);

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(1000L);
  }

  @Test
  @DisplayName("flatMap(onNextMapper, onErrorMapper, onCompleteSupplier)")
  public void ex4_24() {
    Flowable<Integer> original = Flowable.just(1, 2, 0, 4, 5).map(data -> 10 / data);

    Flowable<Integer> flowable = original.flatMap(
        data -> Flowable.just(data),
        error -> Flowable.just(-1),
        () -> Flowable.just(100)
    );

    flowable.subscribe(new DebugSubscriber<>());

  }

  @Test
  @DisplayName("concatMap")
  public void ex4_26() throws InterruptedException {
    Flowable<String> flowable = Flowable.range(10, 3)
        .concatMap(sourceData -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
            .take(2)
            .map(data -> {
              long time = System.currentTimeMillis();
              return time + "ms: [" + sourceData + "] " + data;
            })
        );

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(4000L);
  }
  
  @Test
  @DisplayName("concatMapEager")
  public void ex4_28() throws InterruptedException {
    Flowable<String> flowable = Flowable.range(10, 3)
        .concatMapEager(
            sourceData -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2)
                .map(data -> {
                  long time = System.currentTimeMillis();
                  return time + "ms: [" + sourceData + "] " + data;
                })
        );

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(4000L);
  }

  @Test
  @DisplayName("concatMapEager")
  public void ex4_29() throws InterruptedException {
    Flowable<String> flowable = Flowable.range(10, 3)
        .concatMapEagerDelayError(
            sourceData -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(3)
                .doOnNext(data -> {
                  if (sourceData == 11 && data == 1) {
                    throw new Exception("예외 발생!");
                  }
                })
                .map(data -> "[" + sourceData + "]" + data),
            true
        );

    flowable.subscribe(new DebugSubscriber<>());
    Thread.sleep(4000L);
  }

  @Test
  @DisplayName("buffer size")
  public void ex4_30() throws InterruptedException {
    Flowable<List<Long>> flowable = Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .take(10)
        .buffer(3);

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(3000L);
  }

  @Test
  @DisplayName("buffer boundaryIndicatorSupplier")
  public void ex4_32() throws Exception{
    Flowable<List<Long>> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(7)
        .buffer(
            () -> Flowable.timer(1000L, TimeUnit.MILLISECONDS)
        );

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(4000L);
  }

  @Test
  @DisplayName("toList")
  public void ex4_35(){
    Single<List<Integer>> single = Flowable.just(1, 2, 3, 4, 5).toList();
    single.subscribe(new DebugSingleObserver<>());
  }

  @Test
  @DisplayName("toMap (keySelector)")
  public void ex4_39(){
    Single<Map<Long, String>> single = Flowable.just("1A", "2B", "3C", "1D", "2E")
        .toMap(data -> Long.valueOf(data.substring(0, 1)));

    single.subscribe(new DebugSingleObserver<>());
  }
  
  @Test
  @DisplayName("toMap(keySelector, valueSelector")
  public void ex4_40(){
    Single<Map<Long, String>> single = Flowable.just("1A", "2B", "3C", "1D", "2E")
        .toMap(
            key -> Long.valueOf(key.substring(0, 1)),
            value -> value.substring(1)
        );

    single.subscribe(new DebugSingleObserver<>());
  }

  @Test
  @DisplayName("toMultimap(keySelector)")
  public void ex4_45() throws Exception{
    Single<Map<String, Collection<Long>>> single =
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
        .take(5)
        .toMultimap(data -> data % 2 == 0 ? "짝수" : "홀수");

    single.subscribe(new DebugSingleObserver<>());
    Thread.sleep(3000L);
  }

  @Test
  @DisplayName("filter")
  public void ex4_47() throws InterruptedException {
    Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .filter(data -> data % 2 == 0)
        .take(20);

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(3000L);
  }
  
  @Test
  @DisplayName("distinct")
  public void ex4_49(){
    Flowable<String> flowable = Flowable.just("A", "a", "B", "b", "A", "a", "B")
        .distinct();
    flowable.subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("distinct-keySelector")
  public void ex4_50(){
    Flowable<String> flowable = Flowable.just("A", "a", "B", "b", "A", "a", "B")
        .distinct(data -> data.toLowerCase());

    flowable.subscribe(new DebugSubscriber<>());
  }

  @Test
  @DisplayName("distinctUntilChanged")
  public void ex4_53(){
    Flowable<String> flowable = Flowable.just("A", "a", "a", "A", "a")
        .distinctUntilChanged();

    flowable.subscribe(new DebugSubscriber<>());
  }
  
  @Test
  @DisplayName("distinctUntilChanged")
  public void ex4_54(){
    Flowable<String> flowable = Flowable.just("1", "1.0", "0.1", "0.10", "1")
        .distinctUntilChanged((data1, data2) -> {
          BigDecimal convert1 = new BigDecimal(data1);
          BigDecimal convert2 = new BigDecimal(data2);
          return convert1.compareTo(convert2) == 0;
        });

    flowable.subscribe(new DebugSubscriber<>());
  }
  
  @Test
  @DisplayName("take")
  public void ex4_55() throws InterruptedException {
    Flowable<Long> flowable = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .take(3);

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(4000L);
  }

  @Test
  @DisplayName("takeUntil")
  public void ex4_57() throws InterruptedException {
    Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .takeUntil(data -> data == 5);

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(4000L);
  }

  @Test
  @DisplayName("takeUntil other")
  public void ex4_58() throws InterruptedException {
    Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .takeUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS));

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(3000L);
  }

  @Test
  @DisplayName("takeWhile")
  public void ex4_60() throws InterruptedException {
    Flowable<Long> flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .takeWhile(data -> data != 3);

    flowable.subscribe(new DebugSubscriber<>());

    Thread.sleep(2500L);
  }

}
