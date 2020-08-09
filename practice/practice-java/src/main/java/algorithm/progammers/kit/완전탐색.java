package algorithm.progammers.kit;

import java.util.*;

public class 완전탐색 {

  public int[] 모의고사(int[] answers) {

    List<Integer> p1 = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> p2 = Arrays.asList(2, 1, 2, 3, 2, 4, 2, 5);
    List<Integer> p3 = Arrays.asList(3, 3, 1, 1, 2, 2, 4, 4, 5, 5);

    int p1Count = 0;
    int p2Count = 0;
    int p3Count = 0;

    for (int i = 0; i < answers.length; i++) {
      int answer = answers[i];

      if (p1.get(i % p1.size()) == answer) {
        p1Count++;
      }
      if (p2.get(i % p2.size()) == answer) {
        p2Count++;
      }
      if (p3.get(i % p3.size()) == answer) {
        p3Count++;
      }
    }

    List<Integer> answer = Arrays.asList(p1Count, p2Count, p3Count);
    Set<Integer> set = new TreeSet<>();

    int max = Collections.max(answer);
    for (int i = 0; i < answer.size(); i++) {
      Integer value = answer.get(i);
      if (value > max) {
        max = value;
      } else if (value == max) {
        set.add(i + 1);
      }
    }

    int[] result = new int[set.size()];
    int i = 0;
    for (int value: set) {
      result[i++] = value;
    }

    return set.stream().mapToInt(value -> value.intValue()).toArray();

  }

}
