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
        PayData.builder()
        .payAmount(10_000)
        .billingDate(LocalDate.of(2020,3,1))
        .build(),
        LocalDate.of(2020,4,1)
    );

    assertExpiryDate(
        PayData.builder()
            .payAmount(10_000)
            .billingDate(LocalDate.of(2020,5,5))
            .build(),
        LocalDate.of(2020,6,5)
    );

  }

  @Test
  @DisplayName("납부일과 만료일이 같은일자가 아닌 경우")
  public void bill_expire_not_same() {

    assertExpiryDate(
        PayData.builder()
            .payAmount(10_000)
            .billingDate(LocalDate.of(2019,1,31))
            .build(),
        LocalDate.of(2019,2,28)
    );

    assertExpiryDate(
        PayData.builder()
            .payAmount(10_000)
            .billingDate(LocalDate.of(2019,5,31))
            .build(),
        LocalDate.of(2019,6,30)
    );

    assertExpiryDate(
        PayData.builder()
            .payAmount(10_000)
            .billingDate(LocalDate.of(2020,1,31))
            .build(),
        LocalDate.of(2020,2,29)
    );
  }

  @Test
  @DisplayName("첫납부일과 만료일 다를경우 만원납부")
  public void bill_expire_not_same_만원추가() {
    PayData payData = PayData.builder()
        .firstBillingDate(LocalDate.of(2019,1,31))
        .billingDate(LocalDate.of(2019,2,28))
        .payAmount(10_000)
        .build();

    assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

    PayData payData2 = PayData.builder()
        .firstBillingDate(LocalDate.of(2019,1,30))
        .billingDate(LocalDate.of(2019,2,28))
        .payAmount(10_000)
        .build();

    assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

    PayData payData3 = PayData.builder()
        .firstBillingDate(LocalDate.of(2019,5,31))
        .billingDate(LocalDate.of(2019,6,30))
        .payAmount(10_000)
        .build();

    assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
  }

  private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
    ExpiryDateCalculator calculator = new ExpiryDateCalculator();
    LocalDate expiryDate = calculator.calculateExpiryDate(payData);
    assertEquals(expectedExpiryDate, expiryDate);
  }


}
