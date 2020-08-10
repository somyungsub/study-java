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

  @Test
  @DisplayName("네트워크")
  public void test2(){
    int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    System.out.println(dfsbfs.네트워크(3, computers));
  }

  @Test
  @DisplayName("단어변환")
  public void test3(){
    String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
    System.out.println(dfsbfs.단어변환("hit", "cog", words));

    String[] words2 = {"hot", "dot", "dog", "lot", "log"};
    System.out.println(dfsbfs.단어변환("hit", "cog", words2));
  }

}