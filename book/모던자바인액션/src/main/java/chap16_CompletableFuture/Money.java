package chap16_CompletableFuture;

public enum Money {
  USD(1.1), EUR(1.2);


  private final double rate;

  Money(double rate) {
    this.rate = rate;
  }

  public double getRate() {
    return rate;
  }
}
