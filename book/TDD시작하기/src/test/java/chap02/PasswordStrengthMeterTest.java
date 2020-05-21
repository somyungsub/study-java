package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthMeterTest {

  @Test
  @DisplayName("모든규칙 충족")
  public void password_strong() {
    PasswordStrengthMeter meter = new PasswordStrengthMeter();

    PasswordStrength result = meter.meter("abc12!@AB");
    assertEquals(PasswordStrength.STRONG, result);

    PasswordStrength result2 = meter.meter("abc12!Add");
    assertEquals(PasswordStrength.STRONG, result2);
  }

  @Test
  @DisplayName("중간 - 8자리 미만 + 나머지 충족")
  public void password_normal() {
    PasswordStrengthMeter meter = new PasswordStrengthMeter();

    PasswordStrength result = meter.meter("ab12!@A");
    assertEquals(PasswordStrength.NORMAL, result);

    PasswordStrength result2 = meter.meter("abC12!");
    assertEquals(PasswordStrength.NORMAL, result2);
  }

  @Test
  @DisplayName("약함 - 숫자 포함 x and 나머지 충족")
  public void password_weak() {
    PasswordStrengthMeter meter = new PasswordStrengthMeter();

    PasswordStrength result = meter.meter("ab!@Abba");
    assertEquals(PasswordStrength.WEAK, result);
  }

}
