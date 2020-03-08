package xunit_tdd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCase implements Test{
  private final String name;
  public TestCase(String name) {
    this.name = name;
  }

  public void run(TestResult result) {
    result.testStarted();
    // setup ->테스트 메서드 실행전
    setUp();

    try {
      Method method = getClass().getMethod(name);
      method.invoke(this);  // object
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      result.testFailed();
    }
    tearDown();
  }

  public void setUp() {

  }

  public void tearDown() {

  }
}
