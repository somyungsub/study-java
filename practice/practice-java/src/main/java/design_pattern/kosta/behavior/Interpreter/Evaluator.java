package design_pattern.kosta.behavior.Interpreter;

import java.util.Stack;

/*
  평가하기!!
 */
public class Evaluator {


  // 3 2 +
  public float evaluate(String expression) {
    Stack<Expression> stack = new Stack();
    float result = 0;

    // 토큰라이징
    for (String token : expression.split(" ")) {

      if (isOperator(token)) {
        Expression exp = null;
        if (token.equals("+")) {

          // 2, 3 => Plus Expression를 Stack에 저장
          exp = stack.push(new Plus(stack.pop(), stack.pop()));
        } else if (token.equals("-")) {
          exp = stack.push(new Minus(stack.pop(), stack.pop()));
        }

        if (exp != null) {
          // 3 + 2 -> 5
          result = exp.interpret();
          stack.push(new Number(result));
        }

        if (isNumber(token)) {
          stack.push(new Number(Float.parseFloat(token)));
        }
      }
    }

    return result;
  }

  private boolean isNumber(String token) {
    try {
      Float.parseFloat(token);
      return true;
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    return false;
  }

  private boolean isOperator(String token) {
    return "+,-".contains(token);
  }


}
