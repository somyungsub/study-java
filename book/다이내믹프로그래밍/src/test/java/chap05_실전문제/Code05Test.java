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

}