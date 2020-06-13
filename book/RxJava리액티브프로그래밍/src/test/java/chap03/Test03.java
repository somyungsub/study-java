package chap03;

import io.reactivex.Flowable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test03 {
  @Test
  @DisplayName("이터레이터 패턴사용")
  public void ex3_1() {
    List<String> list = List.of("a", "b", "c");
    Iterator<String> iterator = list.iterator();

    while (iterator.hasNext()) {
      String value = iterator.next();
      System.out.println("value = " + value);
    }
  }

  @Test
  @DisplayName("Flowable 사용")
  public void ex3_3() {
    List<String> list = List.of("a", "b", "C");
    Flowable<String> flowable = Flowable.fromIterable(list);
    flowable.subscribe(o -> System.out.println("o = " + o));
  }

  @Test
  @DisplayName("데이터를 받는 측이 무거운 처리 작업")
  public void ex3_4() throws InterruptedException {
    /*
      생산속도 > 처리속도
       - 생산이 빠르더라도, 처리속도 2초(무거운작업)에 맡게 처리가 이루어짐
       - 오버헤드 제어가 가능
     */
    Flowable.interval(1000L, TimeUnit.MILLISECONDS) // 생산 1초
        .doOnNext(data -> System.out.println("emit = " + System.currentTimeMillis() + " 밀리초: " + data))
        .subscribe(data -> Thread.sleep(2000L)) // 무거운 처리 작업 가정 (2초)
    ;

    Thread.sleep(5000L);
  }

  @Test
  @DisplayName("interval 메서드로 생성한 flowable")
  public void ex3_5() throws InterruptedException {
    /*
      생산 속도 < 처리속도
       - 그럼에도 불구하고, 1초 간격으로 처리됨
       - 처리에 대한 걱정은 필요없음
     */
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .doOnNext(data -> System.out.println("emit : " + System.currentTimeMillis() + " 밀리초 : " + data))
        .subscribe(data -> Thread.sleep(500L));

    Thread.sleep(3000L);
  }

}
