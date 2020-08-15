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

  public String 가장큰수(int[] numbers) {
    for (int i = 0; i < numbers.length; i++) {
      int first = numbers[i];
      int maxIndex = i;
      String result = String.valueOf(first);

      for (int j = i + 1; j < numbers.length; j++) {
        int second = numbers[j];
        String s2 = String.valueOf(second);

        result = max(result, s2);
        maxIndex = result.equals(s2) ? j : maxIndex;

      }
      swap(numbers, i, maxIndex);
    }

    StringBuilder sb = new StringBuilder();
    for (int number : numbers) {
      sb.append(number);
    }

    return sb.toString();
  }

  private String max(String s, String s2) {
    String result = "";
    if (s.charAt(0) != s2.charAt(0)) {
      result = s.charAt(0) > s2.charAt(0) ? s : s2;
    } else if (s.length() == s2.length()) {
      for (int k = 0; k < s.length(); k++) {
        char c = s.charAt(k);
        char c2 = s2.charAt(k);
        if (c != c2) {
          result = c > c2 ? s : s2;
        }
      }
    } else {
      char c = s.charAt(s.length() - 1);
      char c2 = s2.charAt(s2.length() - 1);
      result = c > c2 ? s : s2;
    }

    return result;
  }

  private void swap(int[] numbers, int i, int maxIndex) {
    int tmp = numbers[i];
    numbers[i] = numbers[maxIndex];
    numbers[maxIndex] = tmp;
  }
}
