package algorithm.codility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Lesson1Test {

  @Test
  @DisplayName("test1")
  public void test1(){
    Lesson1 lesson1 = new Lesson1();
    lesson1.binaryGap(32);
    lesson1.binaryGap(30);
    lesson1.binaryGap(9);
    lesson1.binaryGap(529);
    lesson1.binaryGap(15);
    lesson1.binaryGap(Integer.MAX_VALUE);
  }

}