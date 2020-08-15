package algorithm.codility;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Lesson2 {

  public int[] cyclicRotation(int[] A, int K) {

    int[] result = new int[A.length];

    for (int i = 0; i < A.length; i++) {
      int rotateIndex = (i + K) % A.length;
      result[rotateIndex] = A[i];
    }

    return result;
  }

  public int oddOccurrencesInArray(int[] A) {

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < A.length; i++) {
      int value = A[i];
      map.put(value, map.getOrDefault(value, 0) + 1);
    }

    int result = 0;
    for (Integer key : map.keySet()) {
      Integer value = map.get(key);
      if (value % 2 == 1) {
        result = key;
        break;
      }
    }

    return result;
  }
}
