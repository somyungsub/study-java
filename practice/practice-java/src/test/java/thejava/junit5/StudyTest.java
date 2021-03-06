package thejava.junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

//@ExtendWith(FindSlowTestExtension.class)  // 선언적 등록 방법
// DisplayName 을 더 권장
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 동일 인스턴스 내에서 메서드 실행하기 위함, 기본은 메서드마다 각 인스턴스를 생성하여 실행
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

  int value = 1;

  @RegisterExtension
  static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

  @Test
  @DisplayName("스터디만들기") // 권장
  void test() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - order1") // 권장
  @Order(1)
  void test_order() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - order3") // 권장
  @Order(3)
  void test_order3() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - order2") // 권장
  @Order(2)
  void test_order2() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - order22") // 권장
  @Order(2)
  void test_order22() {
    Study study = new Study(10);
    assertNotNull(study);
  }


  @Test
  @DisplayName("스터디만들기 - field")
  void test_field() {
    Study study = new Study(value++);
    System.out.println(study.getLimit());
    System.out.println(this);
  }

  @Test
  @DisplayName("스터디만들기 - field2")
  void test_field2() {
    Study study = new Study(value++);
    System.out.println(study.getLimit());
    System.out.println(this);
  }

  @DisplayName("스터디만들기 - 반복") // 권장
  @RepeatedTest(10)
  void test_repeat() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @DisplayName("스터디만들기 - 반복2") // 권장
  @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
  void test_repeat2(RepetitionInfo info) {
    System.out.println("test");
    System.out.println(info.getCurrentRepetition());
    System.out.println(info.getTotalRepetitions());
  }

  @DisplayName("스터디만들기 - 파라미터")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"날씨가", "많이", "더워지고", "있다"})
  void parameterizedTest(String message) {
    System.out.println(message);
  }


  @DisplayName("스터디만들기 - 파라미터")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"날씨가", "많이", "더워지고", "있다"})
  @EmptySource
  @NullSource
  void parameterizedTest2(String message) {
    System.out.println(message);
  }

  @DisplayName("스터디만들기 - 파라미터")
  @ParameterizedTest(name = "{index} {displayName} message={0}")
  @ValueSource(strings = {"날씨가", "많이", "더워지고", "있다"})
  @NullAndEmptySource
  void parameterizedTest3(String message) {
    System.out.println(message);
  }

  @DisplayName("스터디만들기 - 파라미터")
  @ParameterizedTest(name = "{index} {displayName} num={0}")
  @ValueSource(ints = {10,20,30})
  void parameterizedTest4(Integer num) {
    System.out.println(num);
  }

  @DisplayName("스터디만들기 - 컨버터")
  @ParameterizedTest(name = "{index} {displayName} study={0}")
  @ValueSource(ints = {10,20,30})
  void parameterizedTest5(@ConvertWith(StudyConverter.class) Study study) {
    System.out.println(study.getLimit());
  }

  @DisplayName("스터디만들기 - csv")
  @ParameterizedTest(name = "{index} {displayName} param = {0}, {1}")
  @CsvSource({"10,'자바 스터디'", "20, spring"})
  void parameterizedTest6(Integer limit, String name) {
    final Study study = new Study(limit, name);
    System.out.println(study);
  }

  @DisplayName("스터디만들기 - 인자값조힙")
  @ParameterizedTest(name = "{index} {displayName} study={0}")
  @CsvSource({"10,'자바 스터디'", "20, spring"})
  void parameterizedTest7(ArgumentsAccessor accessor) {
    final Study study = new Study(accessor.getInteger(0), accessor.getString(1)); // 0번째 , 1번째
    System.out.println(study);
  }

  @DisplayName("스터디만들기 - 인자값조힙2")
  @ParameterizedTest(name = "{index} {displayName} study={0}")
  @CsvSource({"10,'자바 스터디'", "20, spring"})
  void parameterizedTest8(@AggregateWith(StudyAggregator.class) Study study) {
    System.out.println(study);
  }

  static class StudyAggregator implements ArgumentsAggregator{
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
      return new Study(accessor.getInteger(0), accessor.getString(1)); // 0번째 , 1번째
    }
  }
  static class StudyConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
      assertEquals(Study.class, targetType, "Can only convert to Study");
      return new Study(Integer.parseInt(source.toString()));
    }
  }


  @Test
  @DisplayName("스터디만들기 - 커스텀 태그") // 권장
  @FastTest
  void test_custom_tag_fast() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - 커스텀 태그2") // 권장
  @SlowTest
  void test_custom_tag_slow() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - extend") // 권장
//  @SlowTest
  void test_custom_tag_slow_extend() throws InterruptedException {
    Thread.sleep(2000L);
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - tag") // 권장
  @Tag("fast")  // -> local 환경에서 테스트 원함
  void test_tag() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - tag2") // 권장
  @Tag("slow")  // -> CI 환경에서 테스트 원함
  void test_tag2() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - 환경변수매칭") // 권장
  @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
  void test_env() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - 환경변수매칭2") // 권장
  @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "ssosso")
  void test_env2() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - java version") // 권장
  @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10, JRE.JAVA_11})
  void test_java_version() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 - java version other") // 권장
  @EnabledOnJre(JRE.OTHER)
  void test_java_version2() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기-운영체제 enable")
  @EnabledOnOs(OS.MAC)
  void test_enabledOnOs() {
    Study study = new Study(10);
    assertNotNull(study);
  }
  @Test
  @DisplayName("스터디만들기-운영체제 disable") // 권장
  @DisabledOnOs(OS.MAC)
  void test_disabledOnOs() {
    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  @DisplayName("스터디만들기 조건에따라") // 권장
  void study_condition() {
    // LOCAL 인 경우만 테스트 실행
    String test_env = System.getenv("TEST_ENV");
//    System.out.println("test_env = " + test_env);
//    assumeTrue("LOCAL".equalsIgnoreCase(test_env));

    assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
      // doing
      System.out.println("local");
      final Study study = new Study(100);
      assertThat(study.getLimit()).isGreaterThan(0);
    });

    assumingThat("DEV".equalsIgnoreCase(test_env), () -> {
      // doing
      System.out.println("dev");
      final Study study = new Study(-10);
      assertThat(study.getLimit()).isLessThan(0);
    });

    Study study = new Study(10);
    assertNotNull(study);
  }

  @Test
  public void throw_confirm() {
    IllegalArgumentException exception
        = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
    assertEquals("limit는 0보다 커야한다.", exception.getMessage());
  }

  @Test
  public void timeout() {
    assertTimeout(Duration.ofSeconds(10), () -> new Study());
  }

  @Test
  public void timeout_fail() {
    assertTimeout(Duration.ofSeconds(1), () -> {
          new Study();
          Thread.sleep(500);
        }
    );
  }

  @Test
  public void timeout_preemptively() {

    // 1초쯤 바로 종료 시킴
    assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
          new Study();
          Thread.sleep(1500);
        }
    );
    // TODO ThreadLocal 살펴보기.. -> 스프링 트랜잭션 ? 공유안됨.. 테스트에서 제대로 적용 안 될 수 있음
  }

  @Test
  @DisplayName("스터디만들기 \uD83D\uDE31 \\xF0\\x9F\\x98\\xB1")
  public void create_new_study() {
    Study study = new Study();
//    assertNotNull(study);
    // 기대, 실제, 실패시 출력 메세지
//    assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다.");
//    assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + " 여야 한다.");
//    assertTrue(1 < 2);
//    assertNull(null);
//    assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다");

    // 한번에 처리하기
    assertAll(
        () -> assertNotNull(study),
        () -> assertNull(study),
        () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + " 여야 한다."),
        () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다")
    );
  }

  @Test
  @DisplayName("스터디만들기 \uD83D\uDE31")
  public void create_new_again() {
    System.out.println("create1");
  }

  @Test
  @Disabled // 소스 정리가 필요한 경우
  public void disable() {
    System.out.println("disable");
  }

  // 전체코드에서 딱 1번 전에 -> 반드시 static, this -> instance가 동일한 경우는 상관 없음
  @BeforeAll
  static void beforeAll() {
    System.out.println("before All");
  }

  // 전체코드에서 딱 1번 후에 -> 반드시 static,  this -> instance가 동일한 경우는 상관 없음
  @AfterAll
  static void afterAll() {
    System.out.println("after All");
  }

  // 각 테스트코드 전에
  @BeforeEach
  void beforeEach() {
    System.out.println("before each");
  }

  // 각 테스트코드 후에
  @AfterEach
  void afterEach() {
    System.out.println("after each");
  }


}