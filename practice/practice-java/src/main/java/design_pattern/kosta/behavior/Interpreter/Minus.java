package design_pattern.kosta.behavior.Interpreter;

public class Minus implements Expression{

  Expression left;
  Expression right;

  public Minus(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }



  @Override
  public float interpret() {

    // 3 4 - => 4 - 3
    return right.interpret() - left.interpret();
  }
}
