package design_pattern.kosta.behavior.state;

// 현재 상태를 저장하는 객체
public class Context {

  private State state; // String, enum ...

  public Context() {

  }

  // setState는 상태 객체에서만 접근해서 사용해라..
  public void setState(State state) {
    this.state = state;
  }

  // 현재상태를 공유하는 용도..
  public State getState() {
    return state;
  }
}
