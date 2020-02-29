package funtional;

import functional.Shop;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {

  /*
    자바 8이전
   */
  @Test
  public void future() {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Double> future = executorService.submit(() -> doSomeLongComputation());

    doSomethingElse();

    try {
      Double result = future.get(5, TimeUnit.SECONDS);
      System.out.println("result = " + result);
    } catch (ExecutionException ee) {
      ee.printStackTrace();
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    } catch (TimeoutException te) {
      te.printStackTrace();
    }
  }

  /*
    자바 8이후
   */
  @Test
  public void completeFuture() {
    Shop shop = new Shop("Base Shop");
    long start = System.nanoTime();

    // 작업 수행
//    Future<Double> future = shop.getAsyncPrice("shoes");  // 작업 수행 중 아래 바로 수행됨
    Future<Double> future = shop.getAsyncPriceException("shoes");  // 작업 수행 중 아래 바로 수행됨
    long invokeTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("invokeTime = " + invokeTime + "msecs");
    doSomethingElse();

    try {
      Double price = future.get();    // 여기 다음은 완료 될때까지 블록
      System.out.println("price = " + price);
      doSomethingElse();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("retrievalTime = " + retrievalTime + "msecs");

  }

  @Test
  public void findPrice() {
    long start = System.nanoTime();
    System.out.println(Shop.findPrices("shoes"));
    long invokeTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("invokeTime = " + invokeTime + "msecs");
  }

  @Test
  public void findPriceParallel() {
    long start = System.nanoTime();
    System.out.println(Shop.findPricesParallel("shoes"));
    long invokeTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("invokeTime = " + invokeTime + "msecs");
  }

  @Test
  public void findPriceAsync() {
    long start = System.nanoTime();
    System.out.println(Shop.findPricesAsync("shoes"));
    long invokeTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("invokeTime = " + invokeTime + "msecs");
  }

  @Test
  public void findPriceAsyncExecutor() {
    long start = System.nanoTime();
    System.out.println(Shop.findPricesAsyncExecutor("shoes"));
    long invokeTime = ((System.nanoTime() - start) / 1_000_000);
    System.out.println("invokeTime = " + invokeTime + "msecs");
  }

  private Double doSomeLongComputation() {
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 0.1;
  }

  private void doSomethingElse() {
    System.out.println("aaaa");
  }


}
