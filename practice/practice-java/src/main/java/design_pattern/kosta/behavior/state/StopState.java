package design_pattern.kosta.behavior.state;

public class StopState implements State {


  @Override
  public void doAction(Context context) {
    System.out.println(" 종료 ~~~ 멈춤 상태");
    context.setState(this);
  }

  public String toString() {
    return "종료상태";
  }
}
