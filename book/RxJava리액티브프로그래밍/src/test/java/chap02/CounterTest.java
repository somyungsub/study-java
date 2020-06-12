package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CounterTest {

  @Test
  @DisplayName("volatile 테스트 - 제어 x")
  public void volatileTest() throws ExecutionException, InterruptedException {

    final Counter counter = new Counter();
    Runnable runnable = () -> {
      for (int i = 0; i < 10_000; i++) {
        counter.increment();
      }
    };

    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Boolean> future1 = executorService.submit(runnable, true);
    Future<Boolean> future2 = executorService.submit(runnable, true);

    if (future1.get() && future2.get()) {
      System.out.println(counter.get());
    } else {
      System.out.println("실패");
    }
    executorService.shutdown();
  }

  @Test
  @DisplayName("atomic")
  public void atomicInteger() throws ExecutionException, InterruptedException {
    final AtomicCounter counter = new AtomicCounter();
    Runnable runnable = () -> {
      for (int i = 0; i < 10_000; i++) {
        counter.increment();
      }
    };

    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Boolean> future1 = executorService.submit(runnable, true);
    Future<Boolean> future2 = executorService.submit(runnable, true);

    if (future1.get() && future2.get()) {
      System.out.println(counter.get());
    } else {
      System.out.println("실패");
    }
    executorService.shutdown();
  }

  @Test
  @DisplayName("atomic - 필드 2개")
  public void atomic_point() throws ExecutionException, InterruptedException {
    final Point point = new Point();
    Runnable runnable = () -> {
      for (int i = 0; i < 20_000; i++) {
        point.rightUp();
      }
    };

    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Boolean> future1 = executorService.submit(runnable, true);
    Future<Boolean> future2 = executorService.submit(runnable, true);

    if (future1.get() && future2.get()) {
      System.out.println(point.getX());
      System.out.println(point.getY());
    } else {
      System.out.println("실패");
    }
    executorService.shutdown();
  }

  @Test
  @DisplayName("synchronized")
  public void sync() throws ExecutionException, InterruptedException {
    final SynchronizedPoint point = new SynchronizedPoint();
    Runnable runnable = () -> {
      for (int i = 0; i < 20_000; i++) {
        point.rightUp();
      }
    };

    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Boolean> future1 = executorService.submit(runnable, true);
    Future<Boolean> future2 = executorService.submit(runnable, true);

    if (future1.get() && future2.get()) {
      System.out.println(point.getX());
      System.out.println(point.getY());
    } else {
      System.out.println("실패");
    }
    executorService.shutdown();
  }

  @Test
  @DisplayName("synchronized2")
  public void sync2() throws ExecutionException, InterruptedException {
    final SynchronizedPoint2 point = new SynchronizedPoint2();
    Runnable runnable = () -> {
      for (int i = 0; i < 20_000; i++) {
        point.rightUp();
      }
    };

    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Boolean> future1 = executorService.submit(runnable, true);
    Future<Boolean> future2 = executorService.submit(runnable, true);

    if (future1.get() && future2.get()) {
      System.out.println(point.getX());
      System.out.println(point.getY());
    } else {
      System.out.println("실패");
    }
    executorService.shutdown();
  }

}