package chap04;

import io.reactivex.Flowable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}
