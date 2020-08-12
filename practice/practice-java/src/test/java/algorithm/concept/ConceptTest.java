package algorithm.concept;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConceptTest {
  멱집합 powerSet = new 멱집합();

  @Test
  @DisplayName("멱집합")
  public void test() {
    int[] S = {1, 2, 3, 4};
    powerSet.powerSet(new int[0], S);
  }

  @Test
  @DisplayName("멱집합2")
  public void test2(){
    powerSet.powerSet2(0);
  }

}