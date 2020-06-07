package chap_appendix_d;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AssetJTest {
  @Test
  @DisplayName("기본메서드")
  public void basic_isEqual() {
    int value = sum(2, 2);

    // 기본 값 비교
    assertThat(value).isEqualTo(4);
    assertThat(value).isNotEqualTo(3);
    assertThat(getNull()).isNull();
    assertThat(new String("aa")).isNotNull();
    assertThat("3").isIn(List.of("1", "2", "3"));
    assertThat("5").isNotIn(List.of("1", "2", "3"));

    // 숫자 or Comparable 인터페이스 구현한 경우
    assertThat(value).isLessThan(5);
    assertThat(value).isLessThanOrEqualTo(5);
    assertThat(value).isGreaterThan(3);
    assertThat(value).isGreaterThanOrEqualTo(4);
    assertThat(value).isBetween(3,5);
    assertThat(value).isBetween(3,4);
    assertThat(value).isBetween(4,4);

    // boolean 관련
    assertThat(true).isTrue();
    assertThat(false).isFalse();
  }

  @Test
  @DisplayName("String 관련 추가")
  public void string() {
    String str = "abcdefgABCDEFGAB";
    String num = "12345";

    assertThat(str).contains("a");
    assertThat(str).contains("bcd");
    assertThat(str).contains("B");
    assertThat(str).contains("a", "b", "c");
    assertThat(str).containsOnlyOnce("ABC");
    assertThat(num).containsOnlyDigits();
//    assertThat(num).containsWhitespaces();
    assertThat(num).containsPattern("[1-9]");

    // 그외 여러가지...
  }

  @Test
  @DisplayName("숫자추가")
  public void number() {
    int num = 0;
    int num2 = 12345;
    int num3 = -num2;

    assertThat(num).isZero();
    assertThat(num2).isNotZero();

    assertThat(num2).isPositive();
    assertThat(num3).isNotPositive();

    assertThat(num2).isNotNegative();
    assertThat(num3).isNegative();
  }

  @Test
  @DisplayName("날짜/시간추가")
  public void date_time() {
    LocalDateTime date = LocalDateTime.now();

    assertThat(date).isAfter(LocalDateTime.of(2020, 6, 7, 0, 0, 0));
    assertThat(date).isBefore(LocalDateTime.of(2020, 6, 7, 23, 59, 59));
  }

  @Test
  @DisplayName("Exception")
  public void exception() {
    assertThatExceptionOfType(RuntimeException.class)
        .isThrownBy(() -> executeRuntimeException());
  }

  @Test
  @DisplayName("SoftAssertions")
  public void soft() {
    SoftAssertions.assertSoftly(softAssertions -> {
      softAssertions.assertThat(1).isBetween(0, 2);
      softAssertions.assertThat(1).isGreaterThan(2);  // 실패
      softAssertions.assertThat(1).isLessThan(0);     // 실패
    });
  }

  @Test
  @DisplayName("as/describedAs")
  public void 메서드설명() {
    String id = "abc";

//    assertThat(id).as("ID 검사").isEqualTo("abcd");
    assertThat(id).describedAs("ID 검사").isEqualTo("abcd");

  }

  private void executeRuntimeException() {
    throw new RuntimeException("런타임 익셉션 발생!!");
  }

  private Object getNull() {
    return null;
  }

  private int sum(int a, int b) {
    return a + b;
  }
}
