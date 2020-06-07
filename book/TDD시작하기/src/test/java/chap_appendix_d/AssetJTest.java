package chap_appendix_d;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

  private Object getNull() {
    return null;
  }

  private int sum(int a, int b) {
    return a + b;
  }
}
