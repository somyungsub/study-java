package algorithm.progammers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution2Test {

  Solution2 solution2;

  @BeforeEach
  public void setup() {
    solution2 = new Solution2();
  }

  @Test
  public void test() {
    int[][] office = new int[3][3];
    office[0] = new int[]{5, -1, 3};
    office[1] = new int[]{6, 3, 4};
    office[2] = new int[]{2, -1, 5};
    System.out.println(solution2.solution(office, 1, 0, new String[]{"go", "go", "right", "go", "right", "go", "left", "go"}));
  }
}