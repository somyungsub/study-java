package chap01;

public class Code01 {

  public int sumRecursion(int n) {
    if (n <= 0) {
      return  0;
    }

    if (n == 1) {
      return 1;
    }

    return n + sumRecursion(n - 1);
  }

  public int sum(int n) {
    int sum = 0;

    for (int i = 1; i <= n; i++) {
      sum += i;
    }

    return sum;
  }

  public int 연습문제1_1_factorial_recursion(int n) {
    if (n <= 1) {
      return 1;
    }
    return n * 연습문제1_1_factorial_recursion(n - 1);
  }

  public int 연습문제1_1_factorial(int n) {
    if (n <= 1) {
      return 1;
    }

    int fact = 1;
    for (int i = 1; i <= n; i++) {
      fact *= i;
    }
    return fact;
  }
}
