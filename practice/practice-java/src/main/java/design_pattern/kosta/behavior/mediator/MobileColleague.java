package design_pattern.kosta.behavior.mediator;

public class MobileColleague extends Colleague {


  public MobileColleague(Mediator mediator) {
    super(mediator);
  }

  @Override
  public void receive(String message) {
    System.out.println("Mobile" + message);
  }


}
