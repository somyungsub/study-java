package chap03_다이내믹프로그래밍;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Code03Test {

  Code03 code03 = new Code03();

  @Test
  @DisplayName("피보나치-메모전략")
  public void fibonacci_메모전략() {
    for (int i = 1; i <= 10; i++) {
      System.out.println(i + " => " + code03.fibonacci(i));
    }
  }

  @Test
  @DisplayName("피보나치-다이내믹 프로그래밍 (DP)")
  public void fibonacci_DP() {
    for (int i = 3; i <= 10; i++) {
      System.out.println(i + " => " + code03.fibonacci_dynamic(i));
    }
  }

  @Test
  @DisplayName("피보나치-다이내믹 프로그래밍 최적화 - log(n) 실행시간")
  public void fibonacci_연습문제3_1() {
    for (int i = 1; i <= 10; i++) {
      System.out.println(i + " => " + code03.fibonacci_logn_연습문제3_1(i));
    }
  }

  @Test
  @DisplayName("부분문자열 다루기 예제 - 완전탐색")
  public void 예제_부분문자열() {
    System.out.println(code03.maxSubStringLength("142124"));
    System.out.println(code03.maxSubStringLength("9430723"));
  }

  @Test
  @DisplayName("부분문자열 다루기 예제 - DP")
  public void 예제_부분문자열_DP() {
    System.out.println(code03.maxSubStringLength_DP("142124"));
    System.out.println(code03.maxSubStringLength_DP("9430723"));
  }

}