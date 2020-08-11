package algorithm.hackerrank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}