package chap05_실전문제;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Practice05Test {

  private Practice05 practice05 = new Practice05();

  @Test
  @DisplayName("연습문제 5-1")
  public void practice5_1() {
    assertEquals(0, practice05.좌표경로수5_1(0, 0));
    assertEquals(1, practice05.좌표경로수5_1(0, 10));
    assertEquals(1, practice05.좌표경로수5_1(10, 0));

    assertEquals(2, practice05.좌표경로수5_1(1, 1));
    assertEquals(10, practice05.좌표경로수5_1(2, 3));
    assertEquals(10, practice05.좌표경로수5_1(3, 2));
  }

  @Test
  @DisplayName("연습문제5-2")
  public void practice5_2() {
    // 인풋??
  }

  @Test
  @DisplayName("연습문제 5-3 - 재귀")
  public void practice5_3_재귀() {
    assertEquals(1, practice05.대각선추가5_3_예제2_재귀(0, 0));
    assertEquals(1, practice05.대각선추가5_3_예제2_재귀(1, 0));
    assertEquals(1, practice05.대각선추가5_3_예제2_재귀(0, 1));

    assertEquals(3, practice05.대각선추가5_3_예제2_재귀(1, 1));
    assertEquals(13, practice05.대각선추가5_3_예제2_재귀(2, 2));
    assertEquals(25, practice05.대각선추가5_3_예제2_재귀(2, 3));
  }

  @Test
  @DisplayName("연습문제 5-3 - DP")
  public void practice5_3_DP() {
    assertEquals(1, practice05.대각선추가5_3_예제2_DP(0, 0));
    assertEquals(1, practice05.대각선추가5_3_예제2_DP(1, 0));
    assertEquals(1, practice05.대각선추가5_3_예제2_DP(0, 1));

    assertEquals(3, practice05.대각선추가5_3_예제2_DP(1, 1));
    assertEquals(13, practice05.대각선추가5_3_예제2_DP(2, 2));
    assertEquals(25, practice05.대각선추가5_3_예제2_DP(2, 3));
  }

}