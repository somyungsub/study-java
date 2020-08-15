package algorithm.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class 정렬ConceptTest {
  정렬 sort = new 정렬();

  @Test
  @DisplayName("합병정렬")
  public void test1(){
    int[] arr = {5, 1, 3, 4, 123, 5452, 112};
    sort.mergeSort(arr, 0, arr.length - 1); // 0~마지막 인덱스

    System.out.println(Arrays.toString(arr));     // 1,3,4,5,112,123,5452
  }

  @Test
  @DisplayName("퀵정렬")
  public void test2(){
    int[] arr = {5, 1, 3, 4, 123, 5452, 112};
    sort.quickSort(arr, 0, arr.length - 1); // 0~마지막 인덱스

    System.out.println(Arrays.toString(arr));     // 1,3,4,5,112,123,5452
  }

}