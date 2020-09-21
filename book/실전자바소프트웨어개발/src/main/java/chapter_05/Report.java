package chapter_05;

public class Report {

  private final Facts facts;
  private final ConditionalAction conditionalAction;
  private final boolean conditionResult;

  public Report(Facts facts, ConditionalAction conditionalAction, boolean conditionResult) {
    this.facts = facts;
    this.conditionalAction = conditionalAction;
    this.conditionResult = conditionResult;
  }

  public Facts getFacts() {
    return facts;
  }

  public ConditionalAction getConditionalAction() {
    return conditionalAction;
  }

  public boolean isConditionResult() {
    return conditionResult;
  }

  @Override
  public String toString() {
    return "Report{" +
      "facts=" + facts +
      ", conditionalAction=" + conditionalAction +
      ", conditionResult=" + conditionResult +
      '}';
  }
}
