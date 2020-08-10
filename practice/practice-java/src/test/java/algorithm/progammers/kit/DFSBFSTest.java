package algorithm.progammers.kit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DFSBFSTest {

  DFSBFS dfsbfs = new DFSBFS();

  @Test
  @DisplayName("타겟넘버")
  public void test(){
    int[] numbers = {1, 1, 1, 1, 1};
    System.out.println(dfsbfs.타겟넘버(numbers, 3));
  }

}