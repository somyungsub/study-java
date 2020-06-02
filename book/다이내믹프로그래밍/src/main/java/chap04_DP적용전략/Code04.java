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
}
