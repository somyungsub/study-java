package algorithm.progammers.kit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class 완전탐색Test {
  완전탐색 full = new 완전탐색();

  @Test
  @DisplayName("모의고사")
  public void test() {
    int[] answers = {1, 2, 3, 4, 5};
    int[] result = full.모의고사(answers);
    for (int i : result) {
      System.out.println("i = " + i);
    }

    int[] answers2 = {1, 3, 2, 4, 2};
    int[] result2 = full.모의고사(answers2);
    for (int i : result2) {
      System.out.println("i = " + i);
    }


  }



}