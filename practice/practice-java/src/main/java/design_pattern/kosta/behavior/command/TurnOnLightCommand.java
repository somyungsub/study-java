package design_pattern.kosta.behavior.command;

public class TurnOnLightCommand implements Command {


  private Light light;

  public TurnOnLightCommand(Light light) {
    this.light = light;
  }

  @Override
  public void execute() {
    // Receiver 에게  전달/ 로깅/ 등등 여러가지 일을 처리
    // 캡슐화 되었다!! 처리에 대한? 메서드에 대한 캡슐화

    // 추가 작업 가능 -> 로깅 / 메세징 등 관련 처리?
    this.light.turnOn();
  }
}
