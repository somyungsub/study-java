package chap02;

import io.reactivex.functions.Action;

public class MainLambda {
  public static void main(String[] args) throws Exception {
    MainLambda target = new MainLambda();
    target.execute();
  }

  private void execute() throws Exception {
    // 익명클래스
    Action action = new Action() {
      @Override
      public void run() throws Exception {
        System.out.println("this 익명 = " + this);
      }
    };

    // 람다식
    Action lambda = () -> System.out.println("this 람다 = " + this);

    action.run();
    lambda.run();

  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
