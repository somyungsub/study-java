package design_pattern.kosta.behavior.Interpreter;

public class Number implements Expression {
  private float number;

  public Number(float number) {
    this.number = number;
  }

  @Override
  public float interpret() {

    return number;
  }
}
