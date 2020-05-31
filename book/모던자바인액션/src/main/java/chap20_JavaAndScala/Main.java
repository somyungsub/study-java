package chap20_JavaAndScala;

import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {
    // 커링
    Stream.of(2,3,4,5)
            .map(multiplyCurry(2))    // 2*2, 2*3, 2*4, 2*5 로 커링
            .forEach(System.out::println);

    final int[] arr = {1, 2, 3};
    arr[1] = 4;
    System.out.println("arr[1] = " + arr[1]);


  }

  public static Function<Integer, Integer> multiplyCurry(int x) {
    return (Integer y) -> x * y;
  }
}
