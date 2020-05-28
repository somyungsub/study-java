package chap19_functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {

  @Test
  @DisplayName("게으른평가")
  public void lazy() {
    MyLinkedList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
    System.out.println("list = " + list);
  }

  @Test
  @DisplayName("게으른평가2")
  public void lazy2() {
    LazyList<Integer> numbers = LazyList.from(2);
    int two = numbers.head();
    int three = numbers.tail().head();
    int four = numbers.tail().tail().head();

    System.out.println(two + " " + three + " " + four);
  }

  @Test
  @DisplayName("게으른평가3")
  public void lazy3() {
    LazyList<Integer> numbers = LazyList.from(2);
    int two = LazyList.primes(numbers).head();
    int three = LazyList.primes(numbers).tail().head();
    int five = LazyList.primes(numbers).tail().tail().head();
    System.out.println(two + " " + three + " " + five);
  }



}