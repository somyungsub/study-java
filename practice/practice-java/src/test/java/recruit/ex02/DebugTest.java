package recruit.ex02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DebugTest {

  @Test
  public void testCase1() {
    Debug debugTest = new Debug();
    assertEquals(debugTest.twoSum(10,20), 30);
  }
  @Test
  public void testCase2() {
    Debug debugTest = new Debug();
    assertEquals(debugTest.twoSum(10,20), 30);
  }
  @Test
  public void testCase3() {
    Debug debugTest = new Debug();
    assertEquals(debugTest.twoSum(10,20+20), 50);
  }
  @Test
  public void testCase4() {
    Debug debugTest = new Debug();
    assertEquals(debugTest.twoSum(10,10), 20);
  }
  @Test
  public void testCase5() {
    Debug debugTest = new Debug();
    assertEquals(debugTest.twoSum(10,20), 30);
  }

}
