package chap02;

import java.math.BigDecimal;

public class Code02 {

  public int 연습문제2_1_(int n) {
    if (n == 1) {
      return 0;
    }

    return 2 * (연습문제2_1_(n - 1) + 1);
  }

  private int[] memo = new int[1000];

  public int fibonacci(int n) {
    if (memo[n] != 0) {
      return memo[n];
    }

    if (n == 1 || n == 2) {
      memo[n] = 1;
    } else {
      memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }
    return memo[n];
  }
}
