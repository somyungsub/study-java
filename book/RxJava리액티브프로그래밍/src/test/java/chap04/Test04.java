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

  
}
