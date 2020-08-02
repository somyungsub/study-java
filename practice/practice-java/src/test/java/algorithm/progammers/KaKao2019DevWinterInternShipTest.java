package algorithm.progammers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KaKao2019DevWinterInternShipTest {

  KaKao2019DevWinterInternShip instance = new KaKao2019DevWinterInternShip();
  @Test
  @DisplayName("크레인 인형뽑기 게임")
  public void test1() {
    int[][] board = {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 3},
        {0, 2, 5, 0, 1},
        {4, 2, 4, 4, 2},
        {3, 5, 1, 3, 1}
    };

    int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};

    System.out.println(instance.crane(board, moves));
  }

}