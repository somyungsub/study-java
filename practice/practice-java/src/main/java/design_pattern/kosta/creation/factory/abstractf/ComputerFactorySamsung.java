package design_pattern.kosta.creation.factory.abstractf;

public class ComputerFactorySamsung implements ComputerFactory {

  // TODO


  @Override
  public Keyboard createKeyboard() {
      return new KeyboardSamsung();
  }

  @Override
  public Mouse createMouse() {
    return new MouseSamsung();
  }

}

