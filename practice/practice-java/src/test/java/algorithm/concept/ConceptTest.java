package algorithm.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConceptTest {
  재귀 recur = new 재귀();

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

}