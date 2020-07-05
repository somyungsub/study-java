package chap02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class CountingFactorizerTest {

  CountingFactorizer countingFactorizer = new CountingFactorizer();

  @Test
  @DisplayName("AtomicLong 활용")
  public void atomic_long() throws ExecutionException, InterruptedException {
    Runnable run1 = () -> {
      for (int i = 0; i < 10_000; i++) {
        countingFactorizer.increment();
      }
    };

    Runnable run2 = () -> {
      for (int i = 0; i < 10_000; i++) {
        countingFactorizer.increment();
      }
    };
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Future<?> submit = executorService.submit(run1);
    Future<?> submit2 = executorService.submit(run2);
    System.out.println("submit.get() = " + submit.get());
    System.out.println("submit2.get() = " + submit2.get());

    if (submit2.isDone()) {
      System.out.println("countingFactorizer2 = " + countingFactorizer.getCount());
    }

    if (submit.isDone()) {
      System.out.println("countingFactorizer1 = " + countingFactorizer.getCount());
    }

    executorService.shutdown();

    long result = countingFactorizer.getCount();
    assertEquals(20_000, result);

  }

}