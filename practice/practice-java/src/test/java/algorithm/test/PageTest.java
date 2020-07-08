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

  @Test
  @DisplayName("3. 정렬 O(n)")
  public void sort_on(){
    int[] arr1 = {1, 2, 3, 5, 9, 20, 33, 34};
    int[] arr2 = {8, 9, 15, 30};

    int[] sort = page.page3(arr1, arr2);

    for (int i : sort) {
      System.out.println("i = " + i);
    }

  }
}