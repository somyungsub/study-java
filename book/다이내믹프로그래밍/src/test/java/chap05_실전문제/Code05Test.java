package chap05_실전문제;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Code05Test {

  private Code05 code05 = new Code05();
  
  @Test
  @DisplayName("5.1 최소 교정 비용 문제 - 재귀")
  public void ex5_1_재귀(){
    assertEquals(3, code05.editDistance_5_1_재귀("CAT", "DOG"));

    assertEquals(1, code05.editDistance_5_1_재귀("CAT", "CAR"));

    assertEquals(3, code05.editDistance_5_1_재귀("SUNDAY", "SATURDAY"));

  }

  @Test
  @DisplayName("5.1 최소 교정 비용 문제 - DP")
  public void ex5_1_DP() {

    assertEquals(3, code05.editDistance_5_1_DP("CAT", "DOG", 3, 3));
    assertEquals(1, code05.editDistance_5_1_DP("CAT", "DOG", 1, 1));
    assertEquals(2, code05.editDistance_5_1_DP("CAT", "DOG", 2, 1));

    assertEquals(1, code05.editDistance_5_1_DP("CAT", "CAR", 3, 3));

    assertEquals(3, code05.editDistance_5_1_DP("SUNDAY", "SATURDAY", 6, 8));
  }


  @Test
  @DisplayName("5.2 직사각형 총 경로수 구하기 - 재귀")
  public void ex5_2_재귀() {
    assertEquals(0, code05.numOfPaths_5_2_재귀(0, 0));
    assertEquals(1, code05.numOfPaths_5_2_재귀(0, 1));
    assertEquals(1, code05.numOfPaths_5_2_재귀(0, 10));
    assertEquals(1, code05.numOfPaths_5_2_재귀(1, 0));
    assertEquals(1, code05.numOfPaths_5_2_재귀(10, 0));
    assertEquals(2, code05.numOfPaths_5_2_재귀(1, 1));
    assertEquals(10, code05.numOfPaths_5_2_재귀(2, 3));
    assertEquals(20, code05.numOfPaths_5_2_재귀(3, 3));
    assertEquals(35, code05.numOfPaths_5_2_재귀(3, 4));
  }

  @Test
  @DisplayName("5.2 직사각형 총 경로수 구하기 - DP")
  public void ex5_2_DP(){
    assertEquals(0, code05.numOfPaths_5_2_DP(0, 0));
    assertEquals(1, code05.numOfPaths_5_2_DP(0, 1));
    assertEquals(1, code05.numOfPaths_5_2_DP(0, 10));
    assertEquals(1, code05.numOfPaths_5_2_DP(1, 0));
    assertEquals(1, code05.numOfPaths_5_2_DP(10, 0));
    assertEquals(2, code05.numOfPaths_5_2_DP(1, 1));
    assertEquals(10, code05.numOfPaths_5_2_DP(2, 3));
    assertEquals(20, code05.numOfPaths_5_2_DP(3, 3));
    assertEquals(35, code05.numOfPaths_5_2_DP(3, 4));
  }

}