package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordStrengthMeterTest {

  private PasswordStrengthMeter meter = new PasswordStrengthMeter();

  private void assertStrength(String password, PasswordStrength strength) {
    PasswordStrength result = meter.meter(password);
    assertEquals(strength, result);
  }

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

    assertStrength("ab!@Abba", PasswordStrength.NORMAL);
    assertStrength("ab!@Abbak", PasswordStrength.NORMAL);

  }

  @Test
  @DisplayName("4. 값이 없는 경우")
  public void password_invalid() {

    assertStrength(null, PasswordStrength.INVALID);
    assertStrength("", PasswordStrength.INVALID);

  }

  @Test
  @DisplayName("5. 대문자를 포함하지 않고 나머지 조건을 충족")
  public void password_not_contains_upper() {

    assertStrength("ab12!@dfddf", PasswordStrength.NORMAL);

  }

  @Test
  @DisplayName("6. 한가지만 충족 - 8자리")
  public void password_onefill_length() {
    assertStrength("abcdefghi", PasswordStrength.WEAK);
  }

  @Test
  @DisplayName("7. 숫자 포함 조건만 충족")
  public void password_only_number() {
    assertStrength("12345", PasswordStrength.WEAK);
  }

  @Test
  @DisplayName("8. 대문자만 충족")
  public void password_only_upper() {
    assertStrength("ABCDE", PasswordStrength.WEAK);
  }

}
