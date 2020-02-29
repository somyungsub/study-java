package design_pattern.kosta.creation.factory.abstractf;

public class FactoryOfComputerFactory {

	// TODO

  public Computer createComputer(String type) {
    ComputerFactory cf = null;
    switch (type) {
      case "LG":
        cf = new ComputerFactoryLG();
        break;
      case "Samsung":
        cf = new ComputerFactorySamsung();
    }

    Keyboard keyboard = cf.createKeyboard();
    Mouse mouse = cf.createMouse();

    return new Computer(keyboard, mouse);
  }
}


class Computer {
  private Keyboard keyboard;
  private Mouse mouse;


  public Computer(Keyboard keyboard, Mouse mouse) {
    this.keyboard = keyboard;
    this.mouse = mouse;
  }

  public Keyboard getKeyboard() {
    return keyboard;
  }

  public Mouse getMouse() {
    return mouse;
  }
}
