package practice.jpashop.domain;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

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
  public void test() {
    assertNotNull(emf);
    assertNotNull(em);
  }

  @Test
  public void test2() {

    Order order = em.find(Order.class, 1L);
    final Long memberId = order.getMemberId();

    final Member member = em.find(Member.class, memberId);
    final Member member1 = order.getMember();


  }

}