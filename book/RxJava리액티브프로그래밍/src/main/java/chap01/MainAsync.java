package chap01;


import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class MainAsync {

  private enum State {
    ADD, MULTIPLY;
  }

  private static State calcMethod;


  public static void main(String[] args) throws InterruptedException {
    sideEffect();
  }

  // 외부영향을 받는 예제
  private static void sideEffect() throws InterruptedException {

    calcMethod = State.ADD;

    Flowable<Long> flowable =
        // 300밀리초마다 데이터를 통지하는 Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 7건까지 통지한다
            .take(7)
            // 각 데이터를 계산한다
            .scan((sum, data) -> {
              if (calcMethod == State.ADD) {
                return sum + data;
              } else {
                return sum * data;
              }
            });

    // 구독하고 받은 데이터를 출력한다
    flowable.subscribe(data -> System.out.println("data=" + data));

    // 잠시 기다리렸다가 계산 방법을 곱셈으로 변경한다
    Thread.sleep(1000);
    System.out.println("계산 방법 변경");
    calcMethod = State.MULTIPLY;

    // 잠시 기다린다
    Thread.sleep(2000);

  }

}
