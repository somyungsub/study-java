package design_pattern.kosta.behavior.mediator;

public class ConcreteColleague extends Colleague {


  public ConcreteColleague(Mediator mediator) {
    super(mediator);
  }

  @Override
  public void receive(String message) {
    System.out.println("Concrete" + message);
  }

}
