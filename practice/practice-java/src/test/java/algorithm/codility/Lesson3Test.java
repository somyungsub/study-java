package algorithm.codility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson3Test {

  Lesson3 lesson3 = new Lesson3();

  @Test
  @DisplayName("수열 - 등차수열")
  public void test1(){
    System.out.println(lesson3.frog(10, 85, 30));
  }

}