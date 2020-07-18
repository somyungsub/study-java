package test.concurrent;

import concurrent.Volataile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

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

  private static ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();
  @Test
  @DisplayName("concurrentHashMap")
  public void computeIfAbsent() {

    final String token = UUID.randomUUID().toString().substring(0, 3);
    String s1 = tokenStore.computeIfAbsent(token, s -> "tT");
    String s2 = tokenStore.computeIfAbsent(token, s -> "tt");

    System.out.println("token = " + token);
    System.out.println("s1 = " + s1);
    System.out.println("s2 = " + s2);

    System.out.println("tokenStore = " + tokenStore);

  }
  @Test
  @DisplayName("concurrentHashMap")
  public void computeIfPresent() {

    final String token = UUID.randomUUID().toString().substring(0, 3);

    tokenStore.put(token, token);
    System.out.println("1.token = " + token);
    String s1 = tokenStore.computeIfPresent(token, (s, s2) -> s + s2);
    String s2 = tokenStore.computeIfPresent(token, (s, s21) -> s + s21);

    System.out.println("token = " + token);
    System.out.println("s1 = " + s1);
    System.out.println("s2 = " + s2);
    System.out.println("tokenStore = " + tokenStore);

  }

  @Test
  @DisplayName("concurrentHashMap - putIfAbsent")
  public void putIfAbsent() {

    final String token = UUID.randomUUID().toString().substring(0, 3);

    System.out.println("1.token = " + token);
    String s1 = tokenStore.putIfAbsent(token, token);
    String s2 = tokenStore.putIfAbsent(token, "tt2");

    System.out.println("token = " + token);
    System.out.println("s1 = " + s1);
    System.out.println("s2 = " + s2);

    System.out.println("tokenStore = " + tokenStore);

  }

  @Test
  @DisplayName("hashmap")
  public void hashmap() {
    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    int size = 10_000_000;
    for (int i = 0; i < size; i++) {
      String value = String.valueOf(i);
      map.put(value, value);
    }

    assertEquals(size, map.size());

  }


  @Test
  @DisplayName("volatile")
  public void volatile가시성() throws ExecutionException, InterruptedException {
    Volataile volataile = new Volataile();

    CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
      for (int i = 0; i < 10_000; i++) {
        volataile.increment();
      }
    });
    CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
      for (int i = 0; i < 10_000; i++) {
        volataile.increment();
      }
    });

    System.out.println(cf1.thenCompose(aVoid -> cf2).get());


    System.out.println(volataile.getCount());


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
