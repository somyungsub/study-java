package design_pattern.kosta.creation.builder;

public class NutritionFacts {

	// TODO

  private final int servingSize;
  private final int servings;
  private final int fat;
  private final int calories;
  private final int sodium;


  public static class Builder{
    private final int servingSize;
    private final int servings;

    private int fat;
    private int calories;
    private int sodium;


    // 세팅 임시 객체 Builder
    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      this.calories = val;
      return this;
    }

    public Builder fat(int val) {
      this.fat = val;
      return this;
    }

    public Builder sodium(int val) {
      this.sodium = val;
      return this;
    }



    public NutritionFacts build() {
      return new NutritionFacts(this);
    }


  }



  private NutritionFacts(Builder builder) {
    this.servingSize = builder.servingSize;
    this.servings = builder.servings;
    this.fat = builder.fat;
    this.calories = builder.calories;
    this.sodium = builder.sodium;

    System.out.println("객체 생성 완료 ~!");
  }


  @Override
  public String toString() {
    return "NutritionFacts{" +
            "servingSize=" + servingSize +
            ", servings=" + servings +
            ", fat=" + fat +
            ", calories=" + calories +
            ", sodium=" + sodium +
            '}';
  }
}
