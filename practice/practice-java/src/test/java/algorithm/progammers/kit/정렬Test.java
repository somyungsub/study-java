package algorithm.progammers.kit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class 정렬Test {

  정렬 sort = new 정렬();

  @Test
  @DisplayName("K번째수")
  public void test(){
    int[] array = {1, 5, 2, 6, 3, 7, 4};
    int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

    int[] ints = sort.K번째수(array, commands);
    for (int anInt : ints) {
      System.out.println("anInt = " + anInt);
    }
  }

  @Test
  @DisplayName("가장큰수")
  public void test2(){
    int[] numbers = {6, 10, 2};
    System.out.println(sort.가장큰수(numbers));

    int[] numbers2 = {3, 30, 34, 5, 9};
    System.out.println(sort.가장큰수(numbers2));
  }

}