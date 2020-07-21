package keyword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KeywordTest {


  @Test
  @DisplayName("assert 테스트")
  public void true_test() {
    int a = 10;
    int b = 5;

    assert a * b == 50;

    System.out.println("=====");
  }

  @Test
  @DisplayName("assert 테스트")
  public void false_test() {
    int a = 10;
    int b = 5;

    assert a * b == 10; // AssertionError

    System.out.println("=====");
  }

  @Test
  @DisplayName("assert 테스트")
  public void false_test_message() {
    int a = 10;
    int b = 5;

    assert a * b == 10 : "test!!"; // AssertionError

    System.out.println("=====");
  }
}
