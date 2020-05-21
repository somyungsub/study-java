package algorithm.progammers;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution1 {

  public int[] test1() {
    System.out.println("test!!!");
    int[][] v = new int[3][2];

    v[0] = new int[]{1, 4};
    v[1] = new int[]{3, 4};
    v[2] = new int[]{3, 10};


    final Set<Map.Entry<Integer, Long>> xEntry = getEntries(v, 0);
    final Set<Map.Entry<Integer, Long>> yEntry = getEntries(v, 1);

    final Map.Entry<Integer, Long> xValue = getValue(xEntry);
    final Map.Entry<Integer, Long> yValue = getValue(yEntry);

    return new int[]{xValue.getKey().intValue(), yValue.getKey().intValue()};
  }

  private Set<Map.Entry<Integer, Long>> getEntries(int[][] v,int index) {
    return Arrays.stream(v)
            .collect(Collectors.groupingBy(ints -> ints[index], Collectors.counting()))
            .entrySet();
  }

  private Map.Entry<Integer, Long> getValue(Set<Map.Entry<Integer, Long>> x) {
    return x.stream().filter(i -> i.getValue() == 1).findAny().get();
  }


  public int solution(String p, String s) {
    int sum = 0;
    for (int i = 0; i < p.length(); i++) {
      sum += getCount(p.charAt(i), s.charAt(i));
    }
    return sum;
  }

  private int getCount(char p, char s) {
    int a = Integer.parseInt(String.valueOf(p)) - Integer.parseInt(String.valueOf(s));
    int b = a < 0 ? a * -1 : a;

    if (b < 5){
      return b;
    }
    else{
      return 10 - b;
    }
  }

}
