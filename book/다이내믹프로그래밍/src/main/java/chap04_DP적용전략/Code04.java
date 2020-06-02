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




}
