package oop.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FamilyTest {

  @Test
  @DisplayName("test")
  public void test() {
    Father father = new Family();
    Mother mother = new Family();

    father.sayParent();
    mother.sayParent();
  }

}