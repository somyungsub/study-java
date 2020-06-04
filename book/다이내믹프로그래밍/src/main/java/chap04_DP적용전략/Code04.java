package chap04_DP적용전략;

public class Code04 {

  public int minPathCost(int[][] cost, int m, int n) {
    if (m == 0 && n == 0) {
      return cost[0][0];
    }
    if (m == 0) {
      return minPathCost(cost, 0, n - 1) + cost[0][n];
    }

    if (n == 0) {
      return minPathCost(cost, m - 1, 0) + cost[m][0];
    }

    int x = minPathCost(cost, m - 1, n);
    int y = minPathCost(cost, m, n - 1);

    return getMin(x, y) + cost[m][n];
  }

  private int getMin(int x, int y) {
    return x < y ? x : y;
  }

  private int getMin(int x, int y, int z) {
    int min = getMin(x, y);
    return min < z ? min : z;
  }

  static final int M = 3;
  static final int N = 4;
  int[][] mem = new int[M][N];

  public int minPathCostMemo(int[][] cost, int m, int n) {
    if (mem[m][n] != 0) {
      return mem[m][n];
    }

    if (m == 0 && n == 0) {
      return (mem[0][0] = cost[0][0]);
    } else if (m == 0) {
      mem[m][n] = minPathCost(cost, 0, n - 1) + cost[0][n];
    } else if (n == 0) {
      mem[m][n] = minPathCost(cost, m - 1, 0) + cost[m][0];
    } else {
      int x = minPathCost(cost, m - 1, n);
      int y = minPathCost(cost, m, n - 1);
      mem[m][n] = getMin(x, y) + cost[m][n];
    }

    return mem[m][n];
  }

  public int minPathCostDP(int[][] cost) {

    // (0,0)
    mem[0][0] = cost[0][0];

    // 맨위 비용저장
    for (int j = 1; j < N; j++) {
      mem[0][j] = mem[0][j - 1] + cost[0][j];
    }

    // 맨왼쪽 비용저장
    for (int i = 1; i < M; i++) {
      mem[i][0] = mem[i - 1][0] + cost[i][0];
    }

    // 나머지 셀 비용 저장
    for (int i = 1; i < M; i++) {
      for (int j = 1; j < N; j++) {
        mem[i][j] = getMin(mem[i - 1][j], mem[i][j - 1]) + cost[i][j];
      }
    }

    return mem[M - 1][N - 1];
  }

  public int minPathCostDP_연습문제4_1(int[][] cost) {

    // (0,0)
    mem[0][0] = cost[0][0];

    // 맨위 비용저장
    for (int j = 1; j < N; j++) {
      mem[0][j] = mem[0][j - 1] + cost[0][j];
    }

    // 맨왼쪽 비용저장
    for (int i = 1; i < M; i++) {
      mem[i][0] = mem[i - 1][0] + cost[i][0];
    }

    // 정방행렬 저장
    int size = M > N ? N : M;
    for (int i = 1; i < size; i++) {
      for (int j = 1; j < size; j++) {
        mem[i][j] = mem[i - 1][j - 1] + cost[i][j];
      }
    }

    // 나머지 셀 비용 저장
    for (int i = 1; i < M; i++) {
      for (int j = 1; j < N; j++) {
        mem[i][j] = getMin(mem[i - 1][j], mem[i][j - 1], mem[i - 1][j - 1]) + cost[i][j];
      }
    }

    return mem[M - 1][N - 1];
  }

  public int countWays(int n) {
    if (n == 1) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    return countWays(n - 1) + countWays(n - 2);
  }

  public int countWays_DP(int n) {
    if (n == 1) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    int[] tiles = new int[n];
    tiles[0] = 1;
    tiles[1] = 2;

    for (int i = 2; i < n; i++) {
      tiles[i] = tiles[i - 1] + tiles[i - 2];
    }
    return tiles[n - 1];
  }

  public int countWays_연습문제4_3(int n) {
    if (n == 1) {
      return 0;
    }
    if (n == 2) {
      return 3;
    }

    return 3 * countWays_연습문제4_3(n - 2);
  }

  public int waysToScore(int n) {
    if (n < 0) {
      return 0;
    }

    if (n == 0) {
      return 1;
    }

    return waysToScore(n - 10) + waysToScore(n - 5) + waysToScore(n - 3);
  }

  public int waysToScore_DP(int n) {
    int[] arr = new int[n + 1];
    arr[0] = 1;

    for (int i = 1; i <= n; i++) {
      if (i - 3 >= 0) {
        arr[i] += arr[i - 3];
      }

      if (i - 5 >= 0) {
        arr[i] += arr[i - 5];
      }

      if (i - 10 >= 0) {
        arr[i] += arr[i - 10];
      }
    }

    return arr[n];
  }

  public int waysToScore_DP_연습문제4_4(int n) {
    int[] arr = new int[n + 1];
    arr[0] = 1;

    for (int i = 1; i <= n; i++) {
      if (i - 3 >= 0) {
        arr[i] += arr[i - 3];
      }

      if (i - 5 >= 0) {
        arr[i] += arr[i - 5];
      }

      if (i - 10 >= 0) {
        arr[i] += arr[i - 10];
      }
    }

    return arr[n] == 1 ? 1 : arr[n] / 2;
  }


}
