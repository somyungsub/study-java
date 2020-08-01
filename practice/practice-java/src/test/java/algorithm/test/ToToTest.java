package algorithm.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToToTest {
  ToTo toTo = new ToTo();
  @Test
  @DisplayName("Test")
  public void test() {
    toTo.test("5");
    toTo.test("1");
    toTo.test("10");
  }

  @Test
  @DisplayName("test1")
  public void test1() {
    toTo.test1("11");
    toTo.test1("12");
    toTo.test1("121");
    toTo.test1("122");
  }

  @Test
  @DisplayName("test2")
  public void test2(){
    toTo.test2("1 2 3 4 5 6");
    toTo.test2("1 3 5 7 7 9");
    toTo.test2("1 2 4 5 6");
    toTo.test2("2 1 3 5 7 9");
    toTo.test2("46 1 3 5 7 9");
  }

  @Test
  @DisplayName("test3")
  public void test3(){
    toTo.test3("1 1 3 4 3 6 3");
  }

  @Test
  @DisplayName("test4")
  public void test4(){
    toTo.test4("우리 우리 우리 하나 우리 국민 삼성 농협 농협 농협 국민 저축");
  }

  @Test
  @DisplayName("test5")
  public void test5() {

    String input = "100 300 10 0 40 0 0 70 65\n" +
        "40 300 20 10 10 20 100 10 0";

    toTo.test5(input);

  }

}