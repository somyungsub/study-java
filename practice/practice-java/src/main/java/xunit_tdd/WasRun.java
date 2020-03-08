package xunit_tdd;

public class WasRun extends TestCase {
  public String log;

  public WasRun(String name) {
    super(name);
    log = "";
  }

  @Override
  public void setUp() {
    log = "setUp";
  }

  public void testMethod() {
    log += " testMethod";
  }

  public void testBrokenMethod() {

  }

  public void tearDown() {
    log += " tearDown";
  }
}
