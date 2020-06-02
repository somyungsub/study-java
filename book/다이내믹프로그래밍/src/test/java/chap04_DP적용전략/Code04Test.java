package chap04_DP적용전략;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Code04Test {

  Code04 code04 = new Code04();

  @Test
  @DisplayName("예제 - 최소비용경로")
  public void 예제1() {
    int[][] cost = {
        {1, 3, 5, 8},
        {4, 2, 1, 7},
        {4, 3, 2, 3}
    };

    System.out.println(code04.minPathCost(cost, 2, 3));
    System.out.println(code04.minPathCost(cost, 1, 2));
    System.out.println(code04.minPathCost(cost, 0, 3));
    System.out.println(code04.minPathCost(cost, 2, 0));

  }

  @Test
  @DisplayName("예제 - 최소비용경로 [메모리제이션]")
  public void 예제1_mem() {
    int[][] cost = {
        {1, 3, 5, 8},
        {4, 2, 1, 7},
        {4, 3, 2, 3}
    };

    System.out.println(code04.minPathCostMemo(cost, 2, 3));
    System.out.println(code04.minPathCostMemo(cost, 1, 2));
    System.out.println(code04.minPathCostMemo(cost, 0, 3));
    System.out.println(code04.minPathCostMemo(cost, 2, 0));

  }

}