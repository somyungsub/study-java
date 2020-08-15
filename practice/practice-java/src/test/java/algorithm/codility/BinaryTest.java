package algorithm.codility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BinaryTest {

  @Test
  @DisplayName("test1")
  public void test1(){
    Binary binary = new Binary();
    binary.binaryGap(32);
    binary.binaryGap(30);
    binary.binaryGap(9);
    binary.binaryGap(529);
    binary.binaryGap(15);
    binary.binaryGap(Integer.MAX_VALUE);
  }

}