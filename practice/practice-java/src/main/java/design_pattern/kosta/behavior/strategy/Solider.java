package design_pattern.kosta.behavior.strategy;

/* Context */
public class Solider {
  private Strategy strategy;

  public void runStrategy() {
    strategy.runStrategy();
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }
}
