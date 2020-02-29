package functional;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Shop {
  private final String name;

  public Shop(String name) {
    this.name = name;
  }

  public double getPrice(String product) {
    return getaCalc(product);
  }


  public Future<Double> getAsyncPrice(String product) {
    CompletableFuture<Double> future = new CompletableFuture<>();
    new Thread(() -> {
      double price = getaCalc(product);
      new RuntimeException("강제 예외 발생");
      future.complete(price);
    }).start();
    return future;
  }

  public Future<Double> getAsyncPriceException(String product) {
    CompletableFuture<Double> future = new CompletableFuture<>();
    new Thread(() -> {
      try {
        double price = getaCalc(product);
        new RuntimeException("강제 예외 발생");
        future.complete(price);
      } catch (Exception e) {
        future.completeExceptionally(e);  // 예외처리 - 도중에 문제 발생시 에러를 포함해서 Future를 종료해줌
      }
    }).start();
    return future;
  }

  public Future<Double> getSupplyAsync(String product) {
    return CompletableFuture.supplyAsync(() -> getaCalc(product));
  }

  public static List<String> findPrices(String product) {
    List<Shop> shops = List.of(
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll"),
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll")
                              );
    return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                         .collect(Collectors.toList());
  }

  // 병렬처리
  public static List<String> findPricesParallel(String product) {
    List<Shop> shops = List.of(
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll"),
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll"),
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll")
                              );
    return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                         .collect(Collectors.toList());
  }

  // 비동기처리
  public static List<String> findPricesAsync(String product) {
    List<Shop> shops = List.of(
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll"),
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll"),
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll")
                              );
    List<CompletableFuture<String>> priceFuture = shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(
              () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
            )
        )
        .collect(Collectors.toList());

    return priceFuture.stream()
                      .map(CompletableFuture::join)   // 모든 비동기 동작이 끝나길 기다림
                      .collect(Collectors.toList());
  }

  // 비동기처리 + Executor
  public static List<String> findPricesAsyncExecutor(String product) {
    List<Shop> shops = List.of(
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll"),
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll"),
                                new Shop("BestPrice"),
                                new Shop("LetsSaveBig"),
                                new Shop("MyFavoriteShop"),
                                new Shop("BuyItAll")
                              );
    // Executor 추가
    Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    });

    List<CompletableFuture<String>> priceFuture = shops.stream()
        .map(shop -> CompletableFuture.supplyAsync(
              () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
            , executor)
        )
        .collect(Collectors.toList());


    return priceFuture.stream()
                      .map(CompletableFuture::join)   // 모든 비동기 동작이 끝나길 기다림
                      .collect(Collectors.toList());
  }

  private String getName() {
    return this.name;
  }

  private Double getaCalc(String product) {
    delay();  // 블록
    if ("shoes".equals(product)) {
      return 6.9;
    } else if ("ipad".equals(product)) {
      return 129.9;
    } else if ("mac".equals(product)) {
      return 499.9;
    }
    return 0.0;
  }

  public static void delay() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
