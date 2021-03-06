package chap16_CompletableFuture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class ShopTest {

  private final List<Shop> shops = List.of(
          new Shop("BestPrice"),
          new Shop("LetsSaveBig"),
          new Shop("MyFavoriteShop"),
          new Shop("BuyItAll1"),
          new Shop("BuyItAll2"),
          new Shop("BuyItAll3"),
          new Shop("BuyItAll4"),
          new Shop("BuyItAll5"),
          new Shop("BuyItAll6"),
          new Shop("BuyItAll7"),
          new Shop("BuyItAll8"),
          new Shop("BuyItAll9"),
          new Shop("BuyItAll10"),
          new Shop("BuyItAll11"),
          new Shop("BuyItAll12")
  );

  private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
    @Override
    public Thread newThread(Runnable r) {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    }
  });

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
  @DisplayName("비블록코드 만들기")
  public void 비블록코드_만들기2() {
    long start = System.nanoTime();
    System.out.println(findPrices3("myPhone27S"));
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("duration = " + duration + " ms");
  }

  @Test
  @DisplayName("비블록코드 만들기-completable compose")
  public void 비블록코드_만들기3() {
    long start = System.nanoTime();
    System.out.println(findPrices2("myPhone27S"));
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("duration = " + duration + " ms");
  }

  @Test
  @DisplayName("비블록코드 만들기-completable combine")
  public void 비블록코드_만들기4() {
    long start = System.nanoTime();
    System.out.println(getExchangePrice("myPhone27S"));
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

  @Test
  @DisplayName("Completable 비동기호출 - 스레드풀")
  public void 비동기호출_스레드풀() {
    long start = System.nanoTime();
    System.out.println(findPricesAsyncExecutor("myPhone27S"));
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("duration = " + duration + " ms");
  }

  @Test
  @DisplayName("getPrice 수정")
  public void Code사용() {
    System.out.println(shops.get(0).getPrice("aaa"));
  }

  @Test
  @DisplayName("실시간처리-thenAccet 사용")
  public void thenAccept사용() {

    long start = System.nanoTime();

    CompletableFuture[] futures = findPriceStream("myPhone~11Pro")
            .map(f -> f.thenAccept(s -> System.out.println(s + " done in  " + ((System.nanoTime() - start) / 1_000_000) + " ms")))
            .toArray(size -> new CompletableFuture[size]);
    CompletableFuture.allOf(futures).join();  // 모든 비동기처리가 완료 될 때 -> join으로
//    CompletableFuture.anyOf(futures).join();  // 완료된게 1개라도 있다면!

    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println("All shops have now responded in " + duration + " ms");
  }

  private List<String> findPrices(String product) {
    return shops
//            .stream()
            .parallelStream() // 병렬로 처리
            .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
            .collect(toList());
  }

  private List<String> findPrices2(String product) {
    return shops
            .stream()
            .map(shop -> shop.getPrice(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(toList());
  }

  private List<String> findPrices3(String product) {
    List<CompletableFuture<String>> priceFutures = shops
            .stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .collect(toList());

    return priceFutures.stream()
            .map(CompletableFuture::join)
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

  private List<String> findPricesAsyncExecutor(String product) {
    List<CompletableFuture<String>> priceFutures = shops
            .stream()
            .map(shop ->
                    CompletableFuture.supplyAsync(
                            () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
                            , executor)
            )
            .collect(toList());
    return priceFutures
            .stream()
            .map(CompletableFuture::join)
            .collect(toList());
  }

  private List<Double> getExchangePrice(String product) {
    ExchangeService exchangeService = new ExchangeService();
    List<CompletableFuture<Double>> futures = shops.stream()
            .map(shop ->
                    CompletableFuture.supplyAsync(() -> shop.getPriceDouble(product))
                            .thenCombine(CompletableFuture.supplyAsync(
                                    () -> exchangeService.getRate(Money.EUR, Money.USD))
                                    .completeOnTimeout(1.2, 1, TimeUnit.SECONDS)
                                    , (price, rate) -> price * rate)
//                    .orTimeout(2, TimeUnit.SECONDS)

            )
            .collect(toList());

    return futures.stream()
            .map(CompletableFuture::join)
            .collect(toList());
  }

  private Stream<CompletableFuture<String>> findPriceStream(String product) {
    return shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(
                    () -> shop.getPrice(product), executor
            ))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(
                    () -> Discount.applyDiscount(quote), executor
            )));
  }


  private void doSomethingElse() {
    System.out.println("==== 다른 작업 수행 ====");
  }

}