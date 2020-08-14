package algorithm.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;

class ConceptTest {
  재귀 recur = new 재귀();
  정렬 sort = new 정렬();

  @Test
  @DisplayName("멱집합")
  public void test() {
    int[] S = {1, 2, 3, 4};
    recur.powerSet(new int[0], S);
  }

  @Test
  @DisplayName("멱집합2")
  public void test2(){
    recur.powerSet2(0);
  }

  @Test
  @DisplayName("순열")
  public void test3() {
    int[] arr = {1, 2, 3, 4};
    recur.printPerm(new int[0], arr);
  }

  @Test
  @DisplayName("순열2")
  public void test4() {
    recur.perm(0);
  }

  @Test
  @DisplayName("힙정렬")
  public void test_heap_sort() {
    int[] arr = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    int[] arr2 = {2, 8, 6, 1, 10, 15, 3, 12, 11};
    sort.heapSort(arr2);
  }

  @Test
  @DisplayName("우선순위큐")
  public void test_prior() {
    int[] arr = {2, 8, 6, 1, 10, 15, 3, 12, 11};
    Queue<Integer> queue = new PriorityQueue<>();

    for (int i : arr) {
      queue.add(i);
    }
    System.out.println(queue);
    while (!queue.isEmpty()) {
      Integer poll = queue.poll();
      System.out.println("poll = " + poll);
    }
  }

  @Test
  @DisplayName("재귀-최대공약수")
  public void test_euclid_method() {
    재귀 recur = new 재귀();
    System.out.println(recur.gcd(10, 5));
    System.out.println(recur.gcd(11, 5));
    System.out.println(recur.gcd(15, 7));
    System.out.println(recur.gcd(20, 5));
    System.out.println(recur.gcd(24, 9));

  }

}