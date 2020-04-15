package thejava.junit5;

import org.junit.Before;
import org.junit.Test;


public class Junit4Test {

  @Before
  public void before() {
    System.out.println("Before");
  }

  @Test
  public void createTest() {
    System.out.println("test");
  }

  @Test
  public void createTest2() {
    System.out.println("test");
  }
}
