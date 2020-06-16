package test.concurrent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class TestConcurrent {
  @Test
  @DisplayName("test")
  public void test() throws ExecutionException, InterruptedException {

    Runnable r = () -> {
      CompletableFuture<String> i1 = CompletableFuture.supplyAsync(() -> test2(0)); // 01234 = 10
      CompletableFuture<String> i2 = CompletableFuture.supplyAsync(() -> test2(5)); // 5679 10 = 37
      CompletableFuture<String> i3 = CompletableFuture.supplyAsync(() -> test2(10)); //
//    System.out.println(i1.thenCompose(s -> i2).thenCompose(s -> i3).get());

      try {
        System.out.println("r1"+i1.thenCombine(i2, (s, s2) -> s.concat(s2)).thenCombine(i3, (s, s2) -> s.concat(s2)).get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      System.out.println("---------");
    };
    Runnable r2 = () -> {
      CompletableFuture<String> i1 = CompletableFuture.supplyAsync(() -> test2(0)); // 01234 = 10
      CompletableFuture<String> i2 = CompletableFuture.supplyAsync(() -> test2(5)); // 5679 10 = 37
      CompletableFuture<String> i3 = CompletableFuture.supplyAsync(() -> test2(10)); //

      try {
        System.out.println("r2"+i1.thenCombine(i2, String::concat).thenCombine(i3, String::concat).get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      System.out.println("---------");
    };

    final ExecutorService executorService = Executors.newFixedThreadPool(10);
    final Future<?> f1 = executorService.submit(r);
    final Future<?> f2 = executorService.submit(r2);

    final Object o = f1.get();
    final Object o1 = f2.get();

    if (f1.isDone()) {
      System.out.println("완료1");
      if (f2.isDone()) {
        System.out.println("완료2");
      }
    }
    executorService.shutdown();
  }

  private String test2(int size) {
    final String name = Thread.currentThread().getName();
    System.out.println(name);
    int sum = 0;
    for (int i = size; i < size + 5; i++) {
      System.out.println(name + " : " + i);
      sum += i;
    }
    return String.valueOf(sum+":");
  }
}
