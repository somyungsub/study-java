package outs.fruit;

/*
  매입 or 매출을 나타내는 enum
 */
public enum IncomeType {
  BUYING("-"),    // 매입
  SELLING("+")    // 매출

  ;

  private String op;   // +,- 를 나타내기 위한 값 정보

  IncomeType(String op) {
    this.op = op;
  }

  public String getOp() {
    return op;
  }
}
