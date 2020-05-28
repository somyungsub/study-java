package chap19_functional;

import java.util.stream.IntStream;

public class PrimeNumber {

  public static IntStream numbers() {
    return IntStream.iterate(2, n -> n + 1);
  }

  public static int head(IntStream numbers) {
    return numbers.findFirst().getAsInt();
  }

  public static IntStream tail(IntStream numbers) {
    return numbers.skip(1);
  }

  public static IntStream primes(IntStream numbers) {
    int head = head(numbers);
    return IntStream.concat(
            IntStream.of(head),
            primes(tail(numbers)).filter(n -> n % head != 0)
    );
  }
}
