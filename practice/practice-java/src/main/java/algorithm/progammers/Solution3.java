package algorithm.progammers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution3 {

  public void test3() {
    System.out.println("test!!!");
  }

  public int solution(int[] numbers, int K) {
    int answer = 0;
    Map<Integer, List<Integer>> map = getData(numbers, K);
    map.forEach((integer, integers) -> {
      if (integers.size() > 0) {
      }
    });


    System.out.println(map);
    return answer;
  }

  private void swap(int a, int b) {

  }

  private Map<Integer, List<Integer>> getData (int[] numbers, int K) {
    int[] copy = Arrays.copyOf(numbers, numbers.length);
    Arrays.sort(copy);

    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int i = 0; i < copy.length; i++) {
      List<Integer> list = new ArrayList<>();

      for (int j = i+1; j < copy.length; j++) {
        if ((copy[i] + K) < copy[j]) {
          list.add(copy[j]);
        }
      }
      map.put(copy[i], list);
    }
    return map;
  }


}
