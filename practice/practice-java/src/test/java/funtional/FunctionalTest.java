package funtional;

import functional.*;
import org.junit.Test;

import java.util.function.DoubleUnaryOperator;

public class FunctionalTest {


  @Test
  public void test14_1_2() {
    /*
       커링
       - f(x,y) = (g(x))(y)
       - 인수를 하나 줄이고, 함수를 반환한다 (같은 포맷)
     */
    DoubleUnaryOperator CtoF = curriedConvert(9.0 / 5, 32);
    DoubleUnaryOperator USDtoGBP = curriedConvert(0.6, 0);
    DoubleUnaryOperator KmtoMi = curriedConvert(0.6214, 0);
  }

  private DoubleUnaryOperator curriedConvert(double f, double b) {
    return (double x) -> x * f + b;
  }

  @Test
  public void test14_2() {

  }

  @Test
  public void test_14_3() {
    Func14_3.primes(5).forEach(System.out::println);
  }

  @Test
  public void test_14_3_2() {
    MyList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));

    LazyList<Integer> list2 = LazyList.from(2);
    Integer two = list2.head();
    Integer three = list2.tail().head();
    Integer four = list2.tail().tail().head();

    System.out.println("two = " + two);
    System.out.println("three = " + three);
    System.out.println("four = " + four);
  }

  @Test
  public void test_14_3_2_lazy() {
    LazyList<Integer> numbers = LazyList.from(2);
    int two = numbers.head();
    int three = numbers.tail().head();
    int four = numbers.tail().tail().head();

    System.out.println("two = " + two);
    System.out.println("three = " + three);
    System.out.println("four = " + four);


  }

}
