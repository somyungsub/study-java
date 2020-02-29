package functional;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Func14_3 {


  public static Stream<Integer> primes(int n) {

    return Stream.iterate(2, integer -> integer + 1)
            .filter(MyMathUtils::isPrime)
            .limit(n);
  }

  public static MyList<Integer> primes(MyList<Integer> numbers) {
    return  new LazyList<>(
            numbers.head(),
            () -> primes(numbers.tail())
    );
  }

}
