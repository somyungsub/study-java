package design_pattern.kosta.creation.factory.abstractf;

public class ComputerFactoryLG implements ComputerFactory {


  // TODO


  @Override
  public Keyboard createKeyboard() {

    return new KeyboardLG();
  }


  @Override
  public Mouse createMouse() {
    return new MouseLG();
  }

}
