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
  @Test
  @DisplayName("예제 - 최소비용경로 [다이내미프로그래밍]")
  public void 예제1_dp() {
    int[][] cost = {
        {1, 3, 5, 8},
        {4, 2, 1, 7},
        {4, 3, 2, 3}
    };
    System.out.println(code04.minPathCostDP(cost));
  }

  @Test
  @DisplayName("연습문제4-1 - 최소비용경로 [다이내미프로그래밍]")
  public void 연습문제4_1() {
    int[][] cost = {
        {1, 3, 5, 8},
        {4, 2, 1, 7},
        {4, 3, 2, 3}
    };
    System.out.println(code04.minPathCostDP_연습문제4_1(cost));
  }

  @Test
  @DisplayName("예제 - 타일")
  public void tile() {
    for (int i = 0; i < 10; i++) {
      System.out.println(code04.countWays(i+1));
    }
  }

  @Test
  @DisplayName("예제 - 타일 DP")
  public void tile_DP() {
    for (int i = 0; i < 10; i++) {
      System.out.println(code04.countWays_DP(i+1));
    }
  }

  @Test
  @DisplayName("연습문제4-3 - 타일 3행")
  public void 연습문제_tile_DP() {
    for (int i = 0; i < 10; i++) {
      System.out.println(code04.countWays_연습문제4_3(i+1));
    }
  }

  @Test
  @DisplayName("예제 - 특정점수 도달하는 경우의수")
  public void score() {
    for (int i = 0; i < 15; i++) {
      System.out.println(i+ " => " +code04.waysToScore(i));
    }
  }

  @Test
  @DisplayName("예제 - 특정점수 도달하는 경우의수 DP")
  public void score_DP() {
    for (int i = 0; i < 15; i++) {
      System.out.println(i+ " => " +code04.waysToScore_DP(i));
    }
  }

  @Test
  @DisplayName("연습문제 4-4 - 특정점수 도달하는 경우의수 DP ")
  public void 연습문제4_4score_DP() {
    for (int i = 0; i < 15; i++) {
      System.out.println(i+ " => " +code04.waysToScore_DP_연습문제4_4(i));
    }
  }

}