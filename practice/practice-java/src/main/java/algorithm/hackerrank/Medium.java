package algorithm.hackerrank;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Medium {

  /*
    최소 변환 값 구하기
   */
  public int formingMagicSquare(int[][] s) {
    int size = s.length;

    IntStream genNumber = IntStream.rangeClosed(1, size * size);
    int sum = genNumber.sum();
    int rowAvg = sum / size;


    return Arrays.stream(s)
        .map(ints -> Arrays.stream(ints).sum())
        .mapToInt(integer -> Math.abs(rowAvg - integer.intValue()))
        .sum();
  }

  /*
    BigInteger 팩토리얼
   */
  public void extraLongFactorials(int n) {
    System.out.println(factorial(BigInteger.valueOf(n)));
  }

  private BigInteger factorial(BigInteger n) {
    if (n.intValue() == 1) {
      return BigInteger.ONE;
    }
    return n.multiply(factorial(BigInteger.valueOf(n.intValueExact() - 1)));
  }

  /*
    Bigger is Greater
   */
  public String biggerIsGreater(String w) {

    int length = w.length();
    int count = 0;
    for (int i = 0; i < length-1; i++) {
      char c1 = w.charAt(i);
      char c2 = w.charAt(i + 1);

      if (c2 >= c1) {
        count++;
      }
    }
    if (count + 1 == length) {
      return "no answer";
    }


    String recur = recur(w);
    if (!w.equals(recur)) {
      return recur;
    } else {
      return biggerIsGreater(w.substring(1).concat(String.valueOf(w.charAt(0))));
    }
  }

  public String recur(String w) {
    if (w.length() == 2) {
      char c1 = w.charAt(0);
      char c2 = w.charAt(1);
      return c1 <= c2
          ? String.valueOf(w.charAt(1)).concat(String.valueOf(w.charAt(0)))
          : w;
    }
    System.out.println("w = " + w);
    return w.charAt(0) + recur(w.substring(1));
  }

  /*
    시간 -> 문자열 변환
   */
  public String timeInWords(int h, int m) {
    String[] hours = {"one", "two", "three", "four", "five", "six", "seven", "eight", "night", "ten", "eleven", "twelve"};
    String[] tenUnit = {"ten", "twenty", "half"};
    String[] minutes = {"quarter", "one", "two", "three", "four", "five", "six", "seven", "eight", "night", "ten", "eleven", "twelve", "thirteen", "fourteen"};

    String hour = hours[h - 1];

    if (m == 0) {
      return hour + " o' clock";
    } else if (m % 10 == 0) {
      return m <= 30 ? tenUnit[(m / 10) - 1] + " minutes past " + hour
          : tenUnit[(60-m / 10) - 1] + " minutes to " + hour;
    } else if (m < 30) {
      String minute = minutes[m % 15];
      return m % 15 == 0 ? minute + " past " + hour : minute + " minutes past " + hour;
    } else {
      int i = 60 - m;
      String minute = minutes[i % 15];
      hour = hours[h];
      return i % 15 == 0 ? minute + " to " + hour : minute + " minutes to " + hour;
    }
  }


}
