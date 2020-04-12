package thejava.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

  @Test
  void test() {
    Study study = new Study();
    assertNotNull(study);
    System.out.println("create");
  }

  @Test
  public void create() {
    System.out.println("create1");
  }

  @Test
  @Disabled // 소스 정리가 필요한 경우
  public void disable() {
    System.out.println("disable");
  }

  // 전체코드에서 딱 1번 전에
  @BeforeAll
  static void beforeAll() {
    System.out.println("before All");
  }

  // 전체코드에서 딱 1번 후에
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