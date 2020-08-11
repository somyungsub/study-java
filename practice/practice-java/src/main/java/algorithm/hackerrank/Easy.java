package algorithm.hackerrank;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Easy {

  /*
    합구하
   */
  public int simpleArraySum(int[] ar) {
    return Arrays.stream(ar).sum();
  }
}
