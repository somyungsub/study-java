package design_pattern.kosta.behavior.command;

public class TurnOffLightCommand implements Command {


  private Light light;
  public TurnOffLightCommand(Light light) {
    this.light = light;
  }

  @Override
  public void execute() {
    light.turnOff();
  }
}
