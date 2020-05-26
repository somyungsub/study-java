package chap16_CompletableFuture;

public class ExchangeService {
  public Double getRate(Money eur, Money usd) {

    double exchange = eur.getRate() / usd.getRate();
    System.out.println("exchange = " + exchange);
    return exchange;
  }
}
