package chap03_다이내믹프로그래밍;

public class Code03 {

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

  public int maxSubStringLength(String str) {
    int n = str.length();
    int maxLen = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i+1; j < n; j+=2) {
        int len = j - i + 1;
        if (maxLen >= len) {
          continue;
        }

        int lSum = 0, rSum = 0;
        for (int k = 0; k < len / 2; k++) {
          lSum += (str.charAt(i + k) - '0');
          rSum += (str.charAt(i + k + len / 2) - '0');
        }

        if (lSum == rSum) {
          maxLen = len;
        }
      }
    }

    return maxLen;
  }
}
