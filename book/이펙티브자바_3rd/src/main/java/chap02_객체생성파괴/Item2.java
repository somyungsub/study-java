package chap02_객체생성파괴;


/*
  생성자에 매개변수가 많다면 빌더를 고려하라
 */
public class Item2 {

  private final int servingSize;
  private final int servings;
  private final int calories;

  public static class Builder {

    private final int servingSize;
    private final int servings;

    private int calories;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }

    public Item2 build() {
      return new Item2(this);
    }

  }

  private Item2(Builder builder) {
    this.servingSize = builder.servingSize;
    this.servings = builder.servings;
    this.calories = builder.calories;
  }
}
