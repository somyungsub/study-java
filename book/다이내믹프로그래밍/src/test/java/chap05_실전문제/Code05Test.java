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

  @Test
  @DisplayName("5.3 문자열 인터리빙 확인 문제 - 재귀")
  public void ex5_3_재귀(){

    assertTrue(code05.isInterleaving_5_3_재귀("xyz", "abcd", "xabyczd"));
  }

  @Test
  @DisplayName("5.3 문자열 인터리빙 확인 문제 - DP")
  public void ex5_3_DP(){

    assertTrue(code05.isInterleaving_5_3_재귀("xyz", "abcd", "xabyczd"));
    assertTrue(code05.isInterleaving_5_3_DP("bcc", "bbca", "bbcbcac"));
  }

  @Test
  @DisplayName("5.4 부분집합의 합 구하기 - 재귀")
  public void ex5_4_재귀(){
    assertTrue(code05.isSubsetSum_5_4_재귀(new int[]{3, 2, 7, 1}, 4, 6));
    assertTrue(code05.isSubsetSum_5_4_재귀(new int[]{3, 2, 7, 1}, 4, 3));
    assertFalse(code05.isSubsetSum_5_4_재귀(new int[]{3, 2, 7, 1}, 4, 20));

    assertTrue(code05.isSubsetSum_5_4_재귀(new int[]{3, 2, 7, 1}, 1, 3));
    assertFalse(code05.isSubsetSum_5_4_재귀(new int[]{3, 2, 7, 1}, 1, 8));

  }
  @Test
  @DisplayName("5.4 부분집합의 합 구하기 - DP")
  public void ex5_4_DP(){
    assertTrue(code05.isSubsetSum_5_4_DP(new int[]{3, 2, 7, 1}, 4, 6));
    assertTrue(code05.isSubsetSum_5_4_DP(new int[]{3, 2, 7, 1}, 4, 3));
    assertFalse(code05.isSubsetSum_5_4_DP(new int[]{3, 2, 7, 1}, 4, 20));

    assertTrue(code05.isSubsetSum_5_4_DP(new int[]{3, 2, 7, 1}, 1, 3));
    assertFalse(code05.isSubsetSum_5_4_DP(new int[]{3, 2, 7, 1}, 1, 8));

  }

  @Test
  @DisplayName("5.5 최장공통 부분 수열 길이 구하기 - 재귀")
  public void ex5_5_재귀(){
    assertEquals(3, code05.lcsLength_5_5_재귀("ABCD", "AEBD", 4, 4));

  }

  @Test
  @DisplayName("5.5 최장공통 부분 수열 길이 구하기 - 메모전략")
  public void ex5_5_메모전략(){
    int[][] memo = new int[30][30];
    for (int i = 0; i < memo.length; i++) {
      for (int j = 0; j < memo[0].length; j++) {
        memo[i][j] = -1;
      }
    }
    code05 = new Code05(memo);
    assertEquals(3, code05.lcsLength_5_5_memo("ABCD", "AEBD", 4, 4));
  }

  @Test
  @DisplayName("5.5 최장공통 부분 수열 길이 구하기 - DP")
  public void ex5_5_DP(){
    assertEquals(3, code05.lcsLength_5_5_DP("ABCD", "AEBD", 4, 4));

  }

  @Test
  @DisplayName("5.6 최장공통 부분 수열 길이 구하기 문자열 출력 - DP")
  public void ex5_6_DP(){
    assertEquals(3, code05.lcsLength_5_6_DP("ABCD", "AEBD", 4, 4));

  }

  @Test
  @DisplayName("5.7 거스름돈 최적화 - 재귀")
  public void ex5_7_재귀() {
    System.out.println(code05.minCoins_5_7_재귀(new int[]{1, 5, 20}, 3, 65));
    System.out.println(code05.minCoins_5_7_재귀(new int[]{1, 5, 15}, 3, 65));
  }


}