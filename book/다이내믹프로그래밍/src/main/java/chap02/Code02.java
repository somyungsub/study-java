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

  public int fibonacci_dynamic(int n) {
    int[] arr = new int[n + 1];
    arr[1] = 1;
    arr[2] = 1;

    for (int i = 3; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }

    return arr[n];
  }

  public int[] logn = new int[1000];

  public int fibonacci_logn_연습문제3_1(int n) {
    /*
      If n is even then k = n/2:
       - F(n) = [2*F(k-1) + F(k)] * F(k)

      If n is odd then k = (n + 1)/2
       - F(n) = F(k)*F(k) + F(k-1)*F(k-1)
     */

    if (n == 0) {
      return 0;
    }

    if (n == 1 || n == 2) {
      return (logn[n] = 1);
    }

    boolean odd = (n & 1) == 1;

    int k = odd
        ? (n + 1) / 2
        : n / 2;

    logn[n] = odd
        ? logn[k] * logn[k] + logn[k - 1] * logn[k - 1]
        : logn[k] * (logn[k] + 2 * logn[k - 1]);

    return logn[n];
  }
}
