package algorithm.hackerrank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MediumTest {

  Medium medium = new Medium();

  @Test
  @DisplayName("마술 정사각")
  public void test() {
    int[][] s = {{5, 3, 4}, {1, 5, 8}, {6, 4, 2}};
    assertEquals(7, medium.formingMagicSquare(s));

    int[][] s2 = {{4, 5, 8}, {2, 4, 1}, {1, 9, 7}};
    assertEquals(14, medium.formingMagicSquare(s2));
  }

}