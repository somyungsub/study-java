package algorithm.codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson1 {
  public int binaryGap(int N) {

    StringBuilder sb = new StringBuilder();

    while (N != 0) {

      int mock = N / 2;
      int rest = N - 2 * mock;
      sb.append(rest);

      N = mock;
    }
    String binStr = sb.reverse().toString();
    System.out.println(binStr);


    int count = 0;
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < binStr.length(); i++) {
      char c = binStr.charAt(i);
      String s = String.valueOf(c);// 0 or 1

      if ("1".equals(s)) {
        result.add(count);
        count = 0;
      } else {
        count++;
      }
    }
    System.out.println("result = " + result);

    Integer max = Collections.max(result);
    System.out.println("max = " + max);
    return max;
  }
}
