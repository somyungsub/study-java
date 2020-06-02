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
  @DisplayName("피보나치-메모전략")
  public void fibonacci_메모전략() {
    for (int i = 1; i <= 10; i++) {
      System.out.println(i + " => " + code02.fibonacci(i));
    }
  }

  @Test
  @DisplayName("피보나치-다이내믹 프로그래밍 (DP)")
  public void fibonacci_DP() {
    for (int i = 3; i <= 10; i++) {
      System.out.println(i + " => " + code02.fibonacci_dynamic(i));
    }
  }

  @Test
  @DisplayName("피보나치-다이내믹 프로그래밍 최적화 - log(n) 실행시간")
  public void fibonacci_연습문제3_1() {
    for (int i = 1; i <= 10; i++) {
      System.out.println(i + " => " + code02.fibonacci_logn_연습문제3_1(i));
    }
  }


}