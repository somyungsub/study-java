package design_pattern.kosta.behavior.mediator;

public abstract class Colleague {

  private Mediator mediator;

  public Colleague(Mediator mediator) {
    this.mediator = mediator;
  }

  // mediator 에게 메세지 전달 : 보낼때!!
  public void sendMessage(String message) {
    mediator.send(message, this);
  }


  // mediator로 부터 message를 받는 함수!
  public abstract void receive(String message);

}
