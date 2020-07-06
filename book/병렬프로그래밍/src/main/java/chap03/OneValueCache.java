package chap03;

import java.math.BigInteger;
import java.util.Arrays;

public class OneValueCache {
  private final BigInteger lastNumber;
  private final BigInteger[] lastFactors;

  public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactors) {
    this.lastNumber = lastNumber;
    this.lastFactors = Arrays.copyOf(lastFactors, lastFactors.length);
  }

  public BigInteger[] getLastFactors(BigInteger integer) {
    if (lastNumber == null || !lastNumber.equals(integer)) {
      return null;
    }
    return Arrays.copyOf(lastFactors, lastFactors.length);
  }

  @Override
  public String toString() {
    return "OneValueCache{" +
        "lastNumber=" + lastNumber +
        ", lastFactors=" + Arrays.toString(lastFactors) +
        '}';
  }
}
