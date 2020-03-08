package xunit_tdd;

public class TestResult {
  int runCount = 0;
  private int errorCount;

  public void testStarted() {
    runCount++;
  }

  public String getSummary() {

    return runCount + " run, " + errorCount + " failed";
  }

  public void testFailed() {
    errorCount++;
  }
}
