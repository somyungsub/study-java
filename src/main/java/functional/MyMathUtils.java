package functional;

import java.util.stream.IntStream;

public class MyMathUtils {
  public static boolean isPrime(int candicate) {
    int candidateRoot = (int) Math.sqrt((double) candicate);
    return IntStream.rangeClosed(2, candidateRoot)
            .noneMatch(i -> candicate % i == 0);
  }
}
