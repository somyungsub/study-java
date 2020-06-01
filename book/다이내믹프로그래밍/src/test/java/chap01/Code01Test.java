package chap01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Code01Test {

  Code01 code01 = new Code01();

  @Test
  @DisplayName("재귀")
  public void recursion() {
    System.out.println("sum = " + code01.sumRecursion(10));
    System.out.println("sum = " + code01.sum(10));
  }

  @Test
  @DisplayName("연습문제1-1")
  public void 연습문제1_1() {
    System.out.println("factorial = " + code01.연습문제1_1_factorial(5));
    System.out.println("factorial = " + code01.연습문제1_1_factorial_recursion(5));
  }

  @Test
  @DisplayName("연습문제1-2")
  public void 연습문제1_2() {
    int[] ints = code01.연습문제1_2_recursion();
    for (int i : ints) {
      System.out.println("i = " + i);
    }
  }

}