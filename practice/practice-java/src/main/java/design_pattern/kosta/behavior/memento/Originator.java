package design_pattern.kosta.behavior.memento;

public class Originator {

  private String state; // 현재상태


  public void setState(String state) {

    this.state = state;
  }

  public String getState() {
    return state;
  }


  // 현재 상태를 메멘토로 저장해라
  public Memento saveStateToMemento() {
    return new Memento(this.state);
  }

  // 메멘토에서 상태를 가져와서 ... 그상태를 현재상태로 업데이트 해라 !!
  public void getStateFromMemento(Memento memento) {
    this.state = memento.getState();
  }
}
