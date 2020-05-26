package chap16_CompletableFuture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

  @Test
  @DisplayName("비동기테스트")
  public void async() {
    Shop shop = new Shop();
    long start = System.nanoTime();
    Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
    long invocationTime = ((System.nanoTime() - start)) / 1_000_000;
    System.out.println("invocationTime = " + invocationTime + " ms");

    // 가격을 계산하는 동안 다른 작업 수행
    doSomethingElse();

    try {
      double price = futurePrice.get(); // 완료될 때까지 블록
      System.out.printf("Price is %.2f%n", price);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }

    long retrievalTime = ((System.nanoTime() - start)) / 1_000_000;
    System.out.println("retrievalTime = " + retrievalTime + " ms");
  }

  private void doSomethingElse() {
    System.out.println("==== 다른 작업 수행 ====");
  }

}