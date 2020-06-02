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

  public int maxSubStringLength_DP(String str) {
    int n = str.length();
    int maxLen = 0;

    int[][] sum = new int[n][n];

    for (int i = 0; i < n; i++) {
      sum[i][i] = str.charAt(i) - '0';
    }

    for (int len = 2; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        int j = i + len - 1;  // 부분열 앞 길이
        int k = len / 2;      // 윗 부분 희소행렬
        sum[i][j] = sum[i][j - k] + sum[j - k + 1][j];  // 점화식

        // 짝수(절반) 이고, 절반합 앞뒤 같고, 길이가 최대길이보다 크다면
        if (len % 2 == 0 && sum[i][j - k] == sum[j - k + 1][j] && len > maxLen) {
          maxLen = len; // 최대길이 저장
        }
      }
    }

    return maxLen;
  }

  // 하향식 접근
  public int factorial(int n) {
    if (n == 1 || n == 0) {
      return 1;
    }

    return n * factorial(n - 1);
  }

  public int factorial_DP(int n) {

    int f = 1;
    for (int i = 2; i <= n; i++) {
      f = f * i;
    }

    return f;
  }

  public void addChildSum(Node root) {
    if (root == null) {
      return;
    }

    addChildSum(root.getLeft());

    addChildSum(root.getRight());

    int finalSum = root.getData();
    if (root.getLeft() != null) {
      finalSum += root.getLeft().getData();
    }

    if (root.getRight() != null) {
      finalSum += root.getRight().getData();
    }

    root.setData(finalSum);
  }
}
