package algorithm.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {

  Page page = new Page();
  @Test
  @DisplayName("1. 피보나치 활용")
  public void page_1(){
    String str = "4\n1\n9\n22\n1134903172";
    String result = page.page1(str);
    System.out.println("result = " + result);
  }
}