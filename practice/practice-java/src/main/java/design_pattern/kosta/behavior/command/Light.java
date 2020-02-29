package design_pattern.kosta.behavior.command;

/*Receiver class*/
public class Light {


  public Light() {
  }

  public void turnOn() {
    System.out.println("Light on");
  }


  public void turnOff() {
    System.out.println("Light off");
  }
}
