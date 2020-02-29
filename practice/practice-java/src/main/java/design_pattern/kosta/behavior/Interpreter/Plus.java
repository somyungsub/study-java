package design_pattern.kosta.behavior.Interpreter;

public class Plus implements Expression {

  // 2 3 4
  // (3 4 + ) 3 +
  Expression left;
  Expression right;

  public Plus(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }



  @Override
  public float interpret() {

    // 3 4 + => 3 + 4
    return left.interpret() + right.interpret();
  }
}
