package algorithm.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {

  Page page = new Page();
  String sep = System.lineSeparator();


  @Test
  @DisplayName("1. 피보나치 활용")
  public void numbe_1(){
    String str = "4\n1\n9\n22\n1134903172";
    String result = page.page1(str);
    System.out.println("result = " + result);
  }

  @Test
  @DisplayName("2. 문자치환")
  public void number_2(){

    StringBuilder sb = new StringBuilder();
    sb.append("2").append(sep)
        .append("http://abc.na1a.com/sto").append(sep)
        .append("abc123@gogg.my.cd");

    String result = page.page2(sb.toString());
    System.out.println(result);
  }
}