package outs.fruit;

/*
  수익에 필요한 정보를 담고 있는 클래스(객체)
 */
public class Income {
  private Fruit fruit;      // 매입 or 매출 발생 관련 과일정보
  private int count;        // 매입 or 매출 갯수
  private IncomeType type;  // 매입 or 매출 타입

  public Income() {
  }

  public Income(Fruit fruit, int count, IncomeType type) {
    this.fruit = fruit;
    this.count = count;
    this.type = type;
  }

  public Fruit getFruit() {
    return fruit;
  }

  public int getCount() {
    return count;
  }

  public IncomeType getType() {
    return type;
  }
}
