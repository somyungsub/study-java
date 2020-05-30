package chap20_JavaAndScala;

import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {
    // 커링
    Stream.of(2,3,4,5)
            .map(multiplyCurry(2))    // 2*2, 2*3, 2*4, 2*5 로 커링
            .forEach(System.out::println);
  }

  public static Function<Integer, Integer> multiplyCurry(int x) {
    return (Integer y) -> x * y;
  }
}
