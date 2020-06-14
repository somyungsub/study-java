package chap04;

import io.reactivex.Flowable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        .just("A","B","C","D","","E","F")
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

  
}
