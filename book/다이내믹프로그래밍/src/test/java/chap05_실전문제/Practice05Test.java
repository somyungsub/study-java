package chap05_실전문제;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Practice05Test {

  private Practice05 practice05 = new Practice05();

  @Test
  @DisplayName("연습문제 5-1")
  public void practice5_1() {
    assertEquals(0, practice05.좌표경로수(0, 0));
    assertEquals(1, practice05.좌표경로수(0, 10));
    assertEquals(1, practice05.좌표경로수(10, 0));

    assertEquals(2, practice05.좌표경로수(1, 1));
    assertEquals(10, practice05.좌표경로수(2, 3));
    assertEquals(10, practice05.좌표경로수(3, 2));
  }

}