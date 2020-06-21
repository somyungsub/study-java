package chap06;

import common.DebugSubscriber;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
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
}
