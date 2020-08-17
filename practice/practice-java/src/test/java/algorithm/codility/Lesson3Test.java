package algorithm.codility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Lesson3Test {

  Lesson3 lesson3 = new Lesson3();

  @Test
  @DisplayName("수열 - 등차수열")
  public void test1(){
    System.out.println(lesson3.frog(10, 85, 30));
    System.out.println(lesson3.frog(1, 1, 3));
    System.out.println(lesson3.frog(3, 999111321, 7));
  }

  @Test
  @DisplayName("원소찾기")
  public void test2() {
    int[] A = {2, 3, 1, 5};
    System.out.println(lesson3.permMissingElem(A));
  }

}