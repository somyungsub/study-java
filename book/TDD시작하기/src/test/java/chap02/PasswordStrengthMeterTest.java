package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthMeterTest {

  private PasswordStrengthMeter meter = new PasswordStrengthMeter();

  @Test
  @DisplayName("1. 모든규칙 충족")
  public void password_strong() {

    assertStrength("abc12!@AB", PasswordStrength.STRONG);
    assertStrength("abc12!Add", PasswordStrength.STRONG);

  }

  @Test
  @DisplayName("2. 중간 - 8자리 미만 + 나머지 충족")
  public void password_normal() {

    assertStrength("ab12!@A", PasswordStrength.NORMAL);
    assertStrength("abC12!", PasswordStrength.NORMAL);

  }

  @Test
  @DisplayName("3. 약함 - 숫자 포함 x and 나머지 충족")
  public void password_weak() {

    assertStrength("ab!@Abba", PasswordStrength.WEAK);
    assertStrength("ab!@Abbak", PasswordStrength.WEAK);

  }

  @Test
  @DisplayName("4. 값이 없는 경우")
  public void password_invalid() {

    assertStrength(null, PasswordStrength.INVALID);
    assertStrength("", PasswordStrength.INVALID);

  }

  private void assertStrength(String password, PasswordStrength strength) {
    PasswordStrength result = meter.meter(password);
    assertEquals(strength, result);
  }

}
