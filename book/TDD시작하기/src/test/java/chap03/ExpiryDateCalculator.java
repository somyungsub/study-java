package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

  public LocalDate calculateExpiryDate(PayData payData) {
    if (payData.getPayAmount() == 10_000) {
      return payData.getBillingDate().plusMonths(1);
    }
    return payData.getBillingDate();
  }
}
