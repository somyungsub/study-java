package algorithm.codility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PracticeTest {
  Practice practice = new Practice();
  @Test
  @DisplayName("test1")
  public void test1(){
    System.out.println(practice.test1("BALLOON"));
    System.out.println(practice.test1("BAONXXOLL"));
    System.out.println(practice.test1("BAOOLLNNOLOLGBAX"));
  }

  @Test
  @DisplayName("test2")
  public void test2(){
    System.out.println(practice.test2("hello"));
  }

  @Test
  @DisplayName("test3")
  public void test3(){

  }

}