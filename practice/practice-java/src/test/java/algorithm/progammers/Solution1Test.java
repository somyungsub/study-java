package algorithm.progammers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution1Test {

  Solution1 solution1;
  @BeforeEach
  public void setBefore() {
    solution1 = new Solution1();
  }

  @Test
  public void test() {
    final int[] ints = solution1.test1();
    System.out.println(ints[0] + "," + ints[1]);
  }

  @Test
  public void sol1() {
    System.out.println(solution1.solution("82195", "64723"));
    System.out.println(solution1.solution("00000000000000000000", "19191919191919191919"));
  }
}