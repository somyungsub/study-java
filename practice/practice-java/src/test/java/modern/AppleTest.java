package modern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class AppleTest {

  private static final String GREEN = "green";

  @Test
  @DisplayName("사과 FP 테스트")
  public void test_apple_fp() {
    List<Apple> inventory = Arrays.asList(
            new Apple("green", 150),
            new Apple("red", 1200),
            new Apple("red", 150),
            new Apple("green", 150)
    );

    filterApples(inventory, (Apple a) -> GREEN.equals(a.getColor()));
  }

  private void filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
    inventory.stream().filter(predicate).forEach(System.out::println);
  }

}