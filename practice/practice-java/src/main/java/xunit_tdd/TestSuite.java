package xunit_tdd;

import org.springframework.core.annotation.AnnotationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSuite implements Test {
  List<Test> tests = new ArrayList<>();

  public TestSuite() {
  }

  public TestSuite(Class<? extends Test> testClass) {
    Arrays.stream(testClass.getDeclaredMethods())
//        .filter(m -> AnnotationUtils.findAnnotation(m, xunit_tdd.annotation.Test.class) != null)
        .filter(m -> m.getName().startsWith("test"))
        .forEach(m ->
            {
              try {
                add(testClass.getConstructor(String.class).newInstance(m.getName()));
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            }
        );

  }

  public void add(Test testBrokenMethod) {
    tests.add(testBrokenMethod);
  }

  public void run(TestResult result) {
    tests.forEach(t -> {
      t.run(result);
    });

  }
}
