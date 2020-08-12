package algorithm.hackerrank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediumTest {

  Medium medium = new Medium();

  @Test
  @DisplayName("마술 정사각")
  public void test() {
    int[][] s = {{5, 3, 4}, {1, 5, 8}, {6, 4, 2}};
    assertEquals(7, medium.formingMagicSquare(s));

    int[][] s2 = {{4, 5, 8}, {2, 4, 1}, {1, 9, 7}};
    assertEquals(14, medium.formingMagicSquare(s2));
  }

  @Test
  @DisplayName("팩토리얼")
  public void test2(){
    medium.extraLongFactorials(25);
  }

  @Test
  @DisplayName("다음철자 큰거찾기")
  public void test3(){
    System.out.println(medium.biggerIsGreater("ab"));
    System.out.println(medium.biggerIsGreater("bb"));
    System.out.println(medium.biggerIsGreater("hefg"));
    System.out.println(medium.biggerIsGreater("dhck"));
    System.out.println(medium.biggerIsGreater("dkhc"));
  }

  @Test
  @DisplayName("다음철자 큰거찾기")
  public void test3_2(){
    System.out.println(medium.biggerIsGreater("lmno"));
    System.out.println(medium.biggerIsGreater("dcba"));
    System.out.println(medium.biggerIsGreater("dcbb"));
    System.out.println(medium.biggerIsGreater("abdc"));
    System.out.println(medium.biggerIsGreater("abcd"));
    System.out.println(medium.biggerIsGreater("fedcbabcd"));
  }

  @Test
  @DisplayName("test1")
  public void test11(){
    String s = "BZA";
    System.out.println(medium.test1(s));    // 4
    String s2 = "AZGB";
    System.out.println(medium.test1(s2));   // 13
  }

  @Test
  @DisplayName("test2")
  public void test22(){
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    list2.add(0);
    list2.add(1);
    list2.add(2);
    list.add(list2);

    List<Integer> list3 = new ArrayList<>();
    list3.add(3);
    list3.add(1);
    list3.add(0);
    list.add(list3);

    System.out.println(medium.test22(list));

  }
  @Test
  @DisplayName("test2")
  public void test22_2(){
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    list2.add(2);
    list2.add(0);
    list.add(list2);

    List<Integer> list3 = new ArrayList<>();
    list3.add(3);
    list3.add(2);
    list.add(list3);

    List<Integer> list4 = new ArrayList<>();
    list4.add(2);
    list4.add(1);
    list.add(list4);

    System.out.println(medium.test22(list));

  }

  @Test
  @DisplayName("test3")
  public void test33(){
    int k = 3; // 서버
    int n = 5; // 수신된 요청
    List<Integer> arrival = List.of(1, 2, 3, 4, 5); // 수신 시각
    List<Integer> load = List.of(6, 3, 4, 4, 4);    // 작업량
    medium.test3(3, arrival, load);
  }

}