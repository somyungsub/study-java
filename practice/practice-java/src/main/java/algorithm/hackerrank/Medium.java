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
}
