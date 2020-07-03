package chap05_실전문제;

import java.util.Arrays;

public class Code05 {

  /*
    5.1 최소 교정 비용 구하기
   */
  public int editDistance_5_1_재귀(String str1, String str2) {
    if (str1.isEmpty()) {
      return str2.length();
    }
    if (str2.isEmpty()) {
      return str1.length();
    }

    if (str1.charAt(0) == str2.charAt(0)) {
      return editDistance_5_1_재귀(str1.substring(1), str2.substring(1));
    }

    int delete = 0;
    int update = 0;
    int insert = 0;

    // 삭제 후 최소교정비용 재귀
    delete = editDistance_5_1_재귀(str1.substring(1), str2);

    // 치환 후 최소교정비용 재귀
    update = editDistance_5_1_재귀(str1.substring(1), str2.substring(1));

    // 삽입 후 최소교정비용 재귀
    insert = editDistance_5_1_재귀(str1, str2.substring(1));

    // 세 연산 이후 최소 값 + 1
    return getMinimum(delete, update, insert) + 1;
  }

  public int editDistance_5_1_DP(String str1, String str2, int m, int n) {

    int[][] EditD = new int[m + 1][n + 1];

    // 1. 맨 위행 채우기
    for (int j = 0; j <= n; j++) {
      EditD[0][j] = j;
    }

    // 2. 맨 왼쪽열 채우기
    for (int i = 0; i <= m; i++) {
      EditD[i][0] = i;
    }

    // 3. 나머지
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          // 1. 두 글자가 같다면
          EditD[i][j] = EditD[i - 1][j - 1];
        } else {
          // 2. 두 글자가 다르다면 : 3연산의 최소값 + 1
          EditD[i][j] = getMinimum(
              EditD[i][j - 1],      // 삽입
              EditD[i - 1][j - 1],  // 치환
              EditD[i - 1][j]       // 삭제
          ) + 1;
        }
      }
    }

    return EditD[m][n];
  }

  private int getMinimum(int delete, int update, int insert) {
    return Math.min(Math.min(delete, update), insert);
  }


  /*
    5.2 직사각형 총경로 수 구하기
   */
  public int numOfPaths_5_2_재귀(int m, int n) {

    // 종료 조건
    if (m == 0 && n == 0) {
      return 0;
    }

    // 첫 번째 행 or 첫 번째 열
    if (m == 0 || n == 0) {
      return 1;
    }

    // 위 접근, 왼쪽 접근
    return numOfPaths_5_2_재귀(m - 1, n) + numOfPaths_5_2_재귀(m, n - 1);
  }

  public int numOfPaths_5_2_DP(int m, int n) {
    int[][] arr = new int[m + 1][n + 1];


    // 1. 1열
    for (int i = 1; i <= m; i++) {
      arr[i][0] = 1;
    }

    // 2. 1행
    for (int j = 1; j <= n; j++) {
      arr[0][j] = 1;
    }

    // 3. 나머지
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
      }
    }

    return arr[m][n];
  }

  public boolean isInterleaving_5_3_재귀(String A, String B, String C) {
    if (A.isEmpty() && B.isEmpty() && C.isEmpty()) {
      return true;
    }

    if (A.length() + B.length() != C.length()) {
      return false;
    }

    boolean caseA = false;
    boolean caseB = false;


    if (!A.isEmpty() && A.charAt(0) == C.charAt(0)) {
      caseA = isInterleaving_5_3_재귀(A.substring(1), B, C.substring(1));
    }

    if (!B.isEmpty() && B.charAt(0) == C.charAt(0)) {
      caseB = isInterleaving_5_3_재귀(A, B.substring(1), C.substring(1));
    }

    return caseA || caseB;

  }

  public boolean isInterleaving_5_3_DP(String A, String B, String C) {
    int M = A.length();
    int N = B.length();
    int lengthC = C.length();

    if (lengthC != M + N) {
      return false;
    }

    boolean[][] matrix = new boolean[M + 1][N + 1];
    matrix[0][0] = true;


    for (int i = 1; i <= M; i++) {
      if (A.charAt(i - 1) != C.charAt(i - 1)) {
        matrix[i][0] = false;
      } else {
        matrix[i][0] = matrix[i - 1][0];
      }
    }

    for (int j = 1; j <= N; j++) {
      if (B.charAt(j - 1) != C.charAt(j - 1)) {
        matrix[0][j] = false;
      } else {
        matrix[0][j] = matrix[0][j - 1];
      }
    }


    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= N; j++) {
        char currentA = A.charAt(i - 1);
        char currentB = B.charAt(j - 1);
        char currentC = C.charAt(i + j - 1);

        if (currentA == currentC && currentB != currentC) {
          matrix[i][j] = matrix[i - 1][j];
        } else if (currentA != currentC && currentB == currentC) {
          matrix[i][j] = matrix[i][j - 1];
        } else if (currentA == currentC && currentB == currentC) {
          matrix[i][j] = matrix[i - 1][j] || matrix[i][j - 1];
        } else {
          matrix[i][j] = false;
        }
      }
    }

    return matrix[M][N];
  }

  public boolean isSubsetSum_5_4_재귀(int[] arr, int n, int X) {

    if (X == 0) {
      return true;
    }

    if (n == 0) {
      return false;
    }

    if (arr[0] > X) {
      return isSubsetSum_5_4_재귀(Arrays.copyOfRange(arr, 1, arr.length), n - 1, X);
    }

    return isSubsetSum_5_4_재귀(Arrays.copyOfRange(arr, 1, arr.length), n - 1, X)
        || isSubsetSum_5_4_재귀(Arrays.copyOfRange(arr, 1, arr.length), n - 1, X - arr[0]);
  }

  public boolean isSubsetSum_5_4_DP(int[] arr, int n, int X) {
    boolean[][] subSum = new boolean[n][X + 1];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= X; j++) {
        subSum[i][j] = false;
      }
    }

    for (int i = 0; i < n; i++) {
      subSum[i][0] = true;
    }

    for (int j = 1; j <= X; j++) {
      subSum[0][j] = arr[0] == j;
    }

    for (int i = 1; i < n; i++) {
      int v = arr[i];
      for (int j = 1; j <= X; j++) {
        if (j < v) {
          subSum[i][j] = subSum[i - 1][j];
        } else if (subSum[i - 1][j]) {
          subSum[i][j] = true;
        } else {
          subSum[i][j] = subSum[i - 1][j - v];
        }
      }
    }

    return subSum[n - 1][X];
  }

  public int lcsLength_5_5_재귀(String X, String Y, int m, int n) {
    if (m == 0 || n == 0) {
      return 0;
    }

    if (X.charAt(m - 1) == Y.charAt(n - 1)) {
      return 1 + lcsLength_5_5_재귀(X, Y, m - 1, n - 1);
    } else {
      return getMax(lcsLength_5_5_재귀(X, Y, m, n - 1), lcsLength_5_5_재귀(X, Y, m - 1, n));
    }
  }

  private int[][] LCS_TABLE;

  public Code05(int[][] LCS_TABLE) {
    this.LCS_TABLE = LCS_TABLE;
  }

  public Code05() {
  }

  public int lcsLength_5_5_memo(String X, String Y, int m, int n) {
    if (m == 0 || n == 0) {
      return 0;
    }

    if (LCS_TABLE[m][n] != -1) {
      return LCS_TABLE[m][n];
    }

    if (X.charAt(m - 1) == Y.charAt(n - 1)) {
      LCS_TABLE[m][n] =  1 + lcsLength_5_5_재귀(X, Y, m - 1, n - 1);
    } else {
      LCS_TABLE[m][n] = getMax(lcsLength_5_5_재귀(X, Y, m, n - 1), lcsLength_5_5_재귀(X, Y, m - 1, n));
    }

    return LCS_TABLE[m][n];
  }

  public int lcsLength_5_5_DP(String X, String Y, int m, int n) {

    int[][] LCS_DP = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        LCS_DP[i][j] = 0;
      }
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
          LCS_DP[i][j] = LCS_DP[i - 1][j - 1] + 1;
        } else {
          LCS_DP[i][j] = getMax(LCS_DP[i - 1][j], LCS_DP[i][j - 1]);
        }
      }
    }

    return LCS_DP[m][n];
  }

  public int lcsLength_5_6_DP(String X, String Y, int m, int n) {
    int[][] LCS_DP = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        LCS_DP[i][j] = 0;
      }
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
          LCS_DP[i][j] = LCS_DP[i - 1][j - 1] + 1;
        } else {
          LCS_DP[i][j] = getMax(LCS_DP[i - 1][j], LCS_DP[i][j - 1]);
        }
      }
    }

    // 추가 !
    int lcsLength = LCS_DP[m][n];

    char[] lcs = new char[lcsLength + 1];
    lcs[lcsLength] = '\0';

    lcsLength--;

    // 우하단 시작
    int i = m, j = n;
    while (i > 0 && j > 0) {

      // X와 Y의 현재 글자가 같으면 LCS 포함
      if (X.charAt(i - 1) == Y.charAt(j - 1)) {
        lcs[lcsLength] = X.charAt(i - 1);
        i--;
        j--;
        lcsLength--;
      } else if (LCS_DP[i - 1][j] > LCS_DP[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }

    System.out.printf("LCS is %s \n", String.valueOf(lcs));

    return LCS_DP[m][n];

  }

  private int getMax(int a , int b) {
    return Math.max(a, b);
  }


  public int minCoins_5_7_재귀(int[] coin, int N, int S) {
    if (S == 0) {
      return 0;
    }

    // 최솟값을 저장하는 변수
    int result = Integer.MAX_VALUE;

    for (int i = 0; i < N; i++) {

      // 액면가가 S보다 같거나 작은 모든 동전에 대해서 재귀
      if (coin[i] <= S) {
        int temp = minCoins_5_7_재귀(coin, N, S - coin[i]);

        // 지금까지 최솟값보다 더 작으면 최소값 할당
        if (temp + 1 < result) {
          result = temp + 1;
        }
      }
    }

    return result;
  }

  public int minCoins_5_7_DP(int[] coin, int N, int S) {

    int[] result = new int[S + 1];

    result[0] = 0;

    for (int i = 1; i <= S; i++) {
      result[i] = Integer.MAX_VALUE;
    }

    // 1원부터 계산해 올라가기
    for (int i = 1; i <= S ; i++) {
      for (int j = 0; j < N; j++) {

        // 현재 금액 보다 작은 액면가의 동전에 대해서만 검사
        if (coin[j] <= i) {
          int temp = result[i - coin[j]];
          System.out.println("temp = " + temp);
          if (temp != Integer.MAX_VALUE && temp + 1 < result[i]) {
            result[i] = temp + 1;
          }
        }
      }
    }

    return result[S];
  }

}
