package chap16_CompletableFuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {

  private final Random random = new Random();
  private String product;
  private String name;

  public Shop(String name) {
    this.name = name;
  }

  public Shop() {
  }

  public String getPrice(String product) {
    // 구현
    double price = calculatePrice(product);
    Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];

    return String.format("%s:%.2f:%s", name, price, code);
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
    return random.nextDouble() * product.charAt(0)
            + product.charAt(1);
  }

  public Future<Double> getPriceAsync(String product) {
    return CompletableFuture.supplyAsync(() -> calculatePrice(product));
  }

  public String getName() {
    return this.name;
  }

  public Double getPriceDouble(String product) {
    String price = getPrice(product);
    return Double.parseDouble(price.split(":")[1]);
  }
//  public Future<Double> getPriceAsync(String product) {
//    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//    new Thread(() -> {
//      try {
//        double price = calculatePrice(product);
//        futurePrice.complete(price);           // 정상 -> 가격 정보를 저장 하고 Future 종료
//      } catch (Exception ex) {
//        futurePrice.completeExceptionally(ex);  // 에러 발생 -> 에러 정보를 저장 하고 Future 종료
//      }
//
//    }).start();
//
//    return futurePrice;
//  }


}
