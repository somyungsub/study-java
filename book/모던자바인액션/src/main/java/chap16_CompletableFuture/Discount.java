package chap16_CompletableFuture;



public class Discount {
  private static Shop shop;

  public Discount(Shop shop) {
    this.shop = shop;
  }

  public enum Code {
    NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

    private final int percentage;

    Code(int percentage) {
      this.percentage = percentage;
    }

  }

  public static String applyDiscount(Quote quote) {
    return quote.getShopName() + " prices is " +
            Discount.apply(quote.getPrice(), quote.getDiscountCode());
  }

  private static double apply(double price, Code discountCode) {
    shop.delay();
    return Double.parseDouble(String.format(String.valueOf(price * (100 - discountCode.percentage) / 100)));
  }
}
