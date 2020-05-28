package chap19_functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static chap19_functional.PrimeNumber.*;
import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberTest {

  @Test
  @DisplayName("소수찾기")
  public void prime() {
    IntStream numbers = numbers();
    int head = head(numbers);
    IntStream filtered = tail(numbers).filter(n -> n % head != 0);

    filtered.forEach(System.out::println);

  }

}