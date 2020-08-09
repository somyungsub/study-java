package algorithm.progammers.kit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 정렬 {

  public int[] K번째수(int[] array, int[][] commands) {
    List<Integer> list = new ArrayList<>();

    for (int[] command : commands) {
      int start = command[0];
      int end = command[1];
      int index = command[2];

      int[] copy = Arrays.copyOfRange(array, start - 1, end);
      Arrays.sort(copy);
      list.add(copy[index - 1]);
    }

    return list.stream().mapToInt(value -> value.intValue()).toArray();
  }
}
