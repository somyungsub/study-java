package chap16_CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

  private final Random random = new Random();

  public double getPrice(String product) {
    // 구현
    return calculatePrice(product);
  }

  public static void delay() {
    try {
      // DB 데이터 조회, 외부 서비스 조회 등.. 오래걸리는 작업을 수행하는 것 처럼
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private double calculatePrice(String product) {
    delay();
    return  random.nextDouble() * product.charAt(0)
            + product.charAt(1);
  }

  public Future<Double> getPriceAsync(String product) {
    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
    new Thread(() -> {
      double pridce = calculatePrice(product);
      futurePrice.complete(pridce);
    }).start();

    return futurePrice;
  }
}
