package chapter_05;

public class SwitchExpressions {

  public void addAction(Facts facts) {
    var dealStage = Stage.valueOf(facts.getFact("stage"));
    var amount = Double.parseDouble(facts.getFact("amount"));
    var forecastedAmount = amount * switch (dealStage) {
      case LEAD -> 0.2;
      case EVALUATING -> 0.5;
      case INTERESTED -> 0.8;
      case CLOSED -> 1;
    };
    facts.addFact("forecastedAmount", String.valueOf(forecastedAmount));
  }
}
