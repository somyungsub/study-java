package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Code02Test {

  Code02 code02 = new Code02();

  @Test
  @DisplayName("연습문제2-1")
  public void 행렬이동경로의수() {
    for (int i = 0; i < 5; i++) {
      System.out.println("i = " + code02.연습문제2_1_(i + 1));
    }
  }

  @Test
  @DisplayName("피보나치")
  public void fibonacci() {
    for (int i = 1; i <= 10; i++) {
      System.out.println(i + " => " + code02.fibonacci(i));
    }

  }

}