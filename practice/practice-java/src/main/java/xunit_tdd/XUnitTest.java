package xunit_tdd;

import org.junit.Test;

public class XUnitTest {
  public static void main(String[] args) {
    TestResult result = new TestResult();
//    TestSuite suite = new TestSuite();
//    new TestCaseTest("testTemplateMethod").run(result).getSummary();
//    new TestCaseTest("testResult").run(result).getSummary();
//    new TestCaseTest("testFailedResultFormatting").run(result).getSummary();
//    new TestCaseTest("testFailedResult").run(result).getSummary();
//    new TestCaseTest("testSuite").run(result).getSummary();
//    suite.add(new TestCaseTest("testTemplateMethod"));
//    suite.add(new TestCaseTest("testResult"));
//    suite.add(new TestCaseTest("testFailedResultFormatting"));
//    suite.add(new TestCaseTest("testFailedResult"));
//    suite.add(new TestCaseTest("testSuite"));
//    suite.run(result);
//    System.out.println(result.getSummary());
//    new TestCaseTest("testTemplateMethod").run();
//    new TestCaseTest("testResult").run();
//    new TestCaseTest("testFailedResultFormatting");
//    new TestCaseTest("testFailedResult");

    TestSuite suite = TestCaseTest.suite();
    suite.run(result);
    System.out.println(result.getSummary());

    TestSuite suite2 = new TestSuite();
    suite2.add(new TestCaseTest("testTemplateMethod"));
    suite2.add(suite);

    TestResult result2 = new TestResult();
    suite2.run(result2);
    System.out.println(result2.getSummary());

  }
}
