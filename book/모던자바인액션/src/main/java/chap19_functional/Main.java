package chap19_functional;

public class Main {
  public static void main(String[] args) {

  }

  public static TrainJourney link(TrainJourney a, TrainJourney b) {
    if (a == null) {
      return b;
    }
    TrainJourney t = a;
    while (t.onward != null) {
      t = t.onward;
    }
    t.onward = b;
    return a;
  }

  public static TrainJourney append(TrainJourney a, TrainJourney b) {
    return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
  }
}
