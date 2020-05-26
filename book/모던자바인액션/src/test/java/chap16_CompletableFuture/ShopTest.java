package chap16_CompletableFuture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.*;

class ShopTest {

  private final List<Shop> shops = List.of(
          new Shop("BestPrice"),
          new Shop("LetsSaveBig"),
          new Shop("MyFavoriteShop"),
          new Shop("BuyItAll")
  );

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

  @Test
  @DisplayName("비동기테스트-에러")
  public void async_error() {
    Shop shop = new Shop();
    long start = System.nanoTime();
    Future<Double> futurePrice = shop.getPriceAsync(null);
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

  @Test
  @DisplayName("비블록코드 만들기")
  public void 비블록코드_만들기() {
    long start = System.nanoTime();
    System.out.println(findPrices("myPhone27S"));
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("duration = " + duration + " ms");
  }

  @Test
  @DisplayName("Completable 비동기호출")
  public void 비동기호출() {
    long start = System.nanoTime();
    System.out.println(findPricesAsync("myPhone27S"));
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("duration = " + duration + " ms");
  }

  private List<String> findPrices(String product) {
    return shops
//            .stream()
            .parallelStream() // 병렬로 처리
            .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
            .collect(toList());
  }

  private List<String> findPricesAsync(String product) {
    List<CompletableFuture<String>> priceFutures = shops
            .stream()
            .map(shop ->
                    CompletableFuture.supplyAsync(
                            () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
                    )
            )
            .collect(toList());
    return priceFutures
            .stream()
            .map(CompletableFuture::join)
            .collect(toList());
  }

  private void doSomethingElse() {
    System.out.println("==== 다른 작업 수행 ====");
  }

}