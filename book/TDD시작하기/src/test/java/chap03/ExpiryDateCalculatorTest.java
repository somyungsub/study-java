package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExpiryDateCalculatorTest {


  @Test
  @DisplayName("1. 만원 납부 -> 만료일 = 납부일 + 1개월")
  public void bill_expire_basic() {

    LocalDate billingDate = LocalDate.of(2020, 3, 1);
    int payAmount = 10_000;
    ExpiryDateCalculator calculator = new ExpiryDateCalculator();

    LocalDate expireDate = calculator.calculateExpiryDate(billingDate, payAmount);

    assertEquals(LocalDate.of(2020, 4, 1), expireDate);

  }


}
