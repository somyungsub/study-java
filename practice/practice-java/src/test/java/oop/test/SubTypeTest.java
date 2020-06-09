package oop.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SubTypeTest {

  @Test
  @DisplayName("Test")
  public void test() {
    SuperType s = new SubType();

    s.abstractProtected();
    s.abstractPublic();
  }

}