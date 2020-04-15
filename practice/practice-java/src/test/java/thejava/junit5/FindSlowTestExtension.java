package thejava.junit5;

import jdk.jfr.Threshold;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

  private long THRESHOLD;

  public FindSlowTestExtension(long l) {
    this.THRESHOLD = l;
  }

  @Override
  public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {

    final ExtensionContext.Store store = getStore(extensionContext);
    store.put("START_TIME", System.currentTimeMillis());
  }

  @Override
  public void afterTestExecution(ExtensionContext extensionContext) throws Exception {

    final Method method = extensionContext.getRequiredTestMethod();
    final SlowTest annotation = method.getAnnotation(SlowTest.class);

    final String testMethod = extensionContext.getRequiredTestMethod().getName();
    final ExtensionContext.Store store = getStore(extensionContext);
    final long start_time = store.remove("START_TIME", long.class);
    long duration = System.currentTimeMillis() - start_time;
    System.out.println("duration = " + duration);
    if (duration > THRESHOLD && annotation == null) {
      System.out.printf("Please consider mark method [%s] with @SlowTest \n", testMethod);
    }


  }

  private ExtensionContext.Store getStore(ExtensionContext extensionContext) {
    final String testClass = extensionContext.getRequiredTestClass().getName();
    final String testMethod = extensionContext.getRequiredTestMethod().getName();
    return extensionContext.getStore(ExtensionContext.Namespace.create(testClass, testMethod));
  }
}
