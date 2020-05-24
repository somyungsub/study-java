package chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
  public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
    if (payAmount == 10_000) {
      return billingDate.plusMonths(1);
    }
    return billingDate;
  }
}
