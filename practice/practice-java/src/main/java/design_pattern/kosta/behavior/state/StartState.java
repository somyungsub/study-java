package design_pattern.kosta.behavior.state;

public class StartState implements State {


  @Override
  public void doAction(Context context) {
    System.out.println("ㅎ상태 스타트 ~~! / 시작함..."); // 상태에 따라서 실행...

    context.setState(this); // 완료한 상태를 context에 알림
  }

  public String toString() {
    return "시작상태";
  }

}
