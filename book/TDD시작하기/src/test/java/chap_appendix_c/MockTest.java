package chap_appendix_c;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;

public class MockTest {

  @Test
  @DisplayName("모의객체 생성")
  public void mock_create() {
    GameNumGen genMock = mock(GameNumGen.class);
  }

  @Test
  @DisplayName("스텁 설정")
  public void stub_setting() {
    GameNumGen genMock = mock(GameNumGen.class);
    // given 설정 : EASY 파라미터에 대한 generate 호출시 -> "123"을 리턴할 것 이다. ** 설정 **
    given(genMock.generate(GameLevel.EASY)).willReturn("123");

    // when & then -> 실행 및 검증
    String num = genMock.generate(GameLevel.EASY);
    assertEquals("123", num);
  }

  @Test
  @DisplayName("스텁 설정 - Exception")
  public void stub_setting_Exception() {
    GameNumGen genMock = mock(GameNumGen.class);
    given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

    // when & then -> 실행 및 검증
    assertThrows(
        IllegalArgumentException.class,
        () -> genMock.generate(null)
    );
  }

  @Test
  @DisplayName("스텁 설정 void - Exception ")
  public void stub_setting_void_Exception() {
    List<String> mockList = mock(List.class);

    willThrow(UnsupportedOperationException.class)
        .given(mockList)  // 모의 객체 -> 스텁
        .clear();         // 모의 객체 -> void 리턴 메서드 실행 (clear)

    // when & then -> 실행 및 검증
    assertThrows(
        UnsupportedOperationException.class,
        () -> mockList.clear()
    );
  }

  @Test
  @DisplayName("인자매칭처리")
  public void arg_matching() {
    GameNumGen genMock = mock(GameNumGen.class);
    given(genMock.generate(any())).willReturn("456");

    String num = genMock.generate(GameLevel.EASY);
    assertEquals("456", num);

    String num2 = genMock.generate(GameLevel.NORMAL);
    assertEquals("456", num2);
  }

  @Test
  @DisplayName("인자매칭처리 - 메서드 인자 2개 이상")
  public void arg_matching_eq() {
    List<String> mockList = mock(List.class);

    given(mockList.set(anyInt(), eq("123"))).willReturn("456");

    String s = mockList.set(5, "123");
    assertEquals("456", s);
  }


}
