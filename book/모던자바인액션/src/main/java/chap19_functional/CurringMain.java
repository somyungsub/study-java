package chap19_functional;

import java.util.function.DoubleUnaryOperator;

public class CurringMain {
  public static void main(String[] args) {
    DoubleUnaryOperator converterCtoF = curriedConverter(9.0 / 5, 32);
    DoubleUnaryOperator converterUSDtoGBP = curriedConverter(0.6, 0);

    System.out.println(converterCtoF.applyAsDouble(1000));
    System.out.println(converterUSDtoGBP.applyAsDouble(500));
  }

  public static DoubleUnaryOperator curriedConverter(double f, double b) {
    return (double x) -> x * f + b;
  }
}
