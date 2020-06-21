package chap06;

import common.DebugSubscriber;
import io.reactivex.Flowable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
