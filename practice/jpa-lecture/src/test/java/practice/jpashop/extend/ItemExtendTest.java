package practice.jpashop.extend;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ItemExtendTest {

  static EntityManagerFactory emf;

  EntityManager em;

  @BeforeAll
  static void setup() {
    emf = Persistence.createEntityManagerFactory("hello");
  }

  @BeforeEach
  void beforeEachSetup(){
    em = emf.createEntityManager();
    em.getTransaction().begin();
  }

  @AfterAll
  static void close() {
    emf.close();
  }
  @AfterEach
  void afterClose() {
    em.getTransaction().commit();
    em.close();
  }

  @Test
  @DisplayName("테이블 생성용")
  public void test() {
    assertNotNull(emf);
    assertNotNull(em);
  }

  @Test
  public void insert_movie() {
    Movie movie = new Movie();
    movie.setActor("sso 배우");
    movie.setDirect("sso 감독");
    movie.setName("바람과 함께사라지다 영화 제목");
    movie.setPrice(12000);

    em.persist(movie);

  }

  @DisplayName("select 테스트")
  @ParameterizedTest(name = "[{index}] {displayName} - [{0}, {1}]")
  @CsvSource(value = {
          "practice.jpashop.extend.ItemExtend, 1",
          "practice.jpashop.extend.Movie, 1",
  })
  public void select(String className, String id) throws ClassNotFoundException {
    final Class<?> clazz = Class.forName(className);
    final Object select = em.find(clazz, Long.valueOf(id));
    System.out.println("select = " + select);
  }

}