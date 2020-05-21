package algorithm.progammers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution3Test {

  Solution3 solution3;

  @BeforeEach
  public void setup() {
    solution3 = new Solution3();
  }

  @Test
  void test3() {
    int[] a = new int[]{10, 40, 30, 20};
    solution3.solution(a, 20);
  }


}