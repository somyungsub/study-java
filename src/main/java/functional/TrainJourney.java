package functional;

public class TrainJourney {
  public int price;
  public TrainJourney onward;

  public TrainJourney(int price, TrainJourney onward) {
    this.price = price;
    this.onward = onward;
  }

  // a에 영향을 미침
  public static TrainJourney link(TrainJourney a, TrainJourney b) {
    if (a == null) {
      return b;
    }

    TrainJourney t = a; // t -> a
    while (t.onward != null) {
      t = t.onward; // t.onward
    }
    t.onward = b;   // t.onward -> b
    return a;
  }

  // 부수효과 제거
  public static TrainJourney append(TrainJourney a, TrainJourney b) {
    return a == null ? b : new TrainJourney(a.price, append(a.onward, b));  // 재귀
  }


}
