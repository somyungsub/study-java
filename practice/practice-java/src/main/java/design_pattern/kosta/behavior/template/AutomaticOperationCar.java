package design_pattern.kosta.behavior.template;

public class AutomaticOperationCar extends Car {

  @Override
  protected void play() {
    System.out.println("자동 D드라이브로 넣기");
  }


  // hook method
  @Override
  protected void runSomething() {

    System.out.println("Auto 넣기");
  }
}
