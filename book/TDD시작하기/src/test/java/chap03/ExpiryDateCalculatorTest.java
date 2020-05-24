package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExpiryDateCalculatorTest {


  @Test
  @DisplayName("1. 만원 납부 -> 만료일 = 납부일 + 1개월")
  public void bill_expire_basic() {

    assertExpiryDate(
        10_000,
        LocalDate.of(2020,3,1),
        LocalDate.of(2020,4,1)
    );

    assertExpiryDate(
        10_000,
        LocalDate.of(2020,5,5),
        LocalDate.of(2020,6,5)
    );

  }

  private void assertExpiryDate(int amount, LocalDate billingDate, LocalDate expectedExpiryDate) {
    ExpiryDateCalculator calculator = new ExpiryDateCalculator();
    LocalDate expireDate = calculator.calculateExpiryDate(billingDate, amount);
    assertEquals(expectedExpiryDate, expireDate);
  }


}
