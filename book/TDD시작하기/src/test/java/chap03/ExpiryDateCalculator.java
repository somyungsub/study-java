package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

  public LocalDate calculateExpiryDate(PayData payData) {

    int addedMonths = payData.getPayAmount() / 10_000;

    if (payData.getFirstBillingDate() != null) {
      return expiryDateUsingFirstBillingDate(payData, addedMonths);
    } else {
      return payData.getBillingDate().plusMonths(addedMonths);
    }
  }

  private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
    LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
    int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

    if (isSameDayOfMonth(candidateExp, dayOfFirstBilling)) {
      int lengthOfMonth = lastDayOfMonth(candidateExp);

      if (isFirstBatLengthCompare(dayOfFirstBilling, lengthOfMonth)) {
        return candidateExp.withDayOfMonth(lengthOfMonth);
      }
      return candidateExp.withDayOfMonth(dayOfFirstBilling);
    }

    return candidateExp;
  }

  private int lastDayOfMonth(LocalDate candidateExp) {
    return YearMonth.from(candidateExp).lengthOfMonth();
  }

  private boolean isFirstBatLengthCompare(int dayOfFirstBilling, int lengthOfMonth) {
    return lengthOfMonth < dayOfFirstBilling;
  }

  private boolean isSameDayOfMonth(LocalDate candidateExp, int dayOfFirstBilling) {
    return dayOfFirstBilling != candidateExp.getDayOfMonth();
  }
}
