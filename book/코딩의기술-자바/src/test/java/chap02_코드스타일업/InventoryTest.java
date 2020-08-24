package chap02_코드스타일업;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ConcurrentModificationException;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryTest {

  Inventory inventory;

  @BeforeAll
  void setUp() {
    IntStream intStream = IntStream.rangeClosed(1, 50);
    inventory = new Inventory(intStream.boxed().collect(toList()));
  }

  @Test
  @DisplayName("2.4 순회하며 컬렉션 수정하지 않기")
  public void test(){
    assertThrows(
      ConcurrentModificationException.class,
      () -> inventory.disposeContaminatedSupplies());
  }

  @Test
  @DisplayName("2.4 순회하며 컬렉션 수정하지 않기")
  public void test2(){
    inventory.disposeContaminatedSupplies_iter();
  }

}