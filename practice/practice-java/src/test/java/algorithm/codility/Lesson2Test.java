package algorithm.codility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class Lesson2Test {

  Lesson2 lesson2 = new Lesson2();

  @Test
  @DisplayName("로테이션")
  public void test1(){
    int[] arr = {1, 2, 3, 4};

    int[] ints = lesson2.cyclicRotation(arr, 3);
    System.out.println(Arrays.toString(ints));
  }

  @Test
  @DisplayName("test2")
  public void test2(){
    int[] arr = {9, 3, 9, 3, 9, 7, 9};
    System.out.println(lesson2.oddOccurrencesInArray(arr)); // 7
  }

}