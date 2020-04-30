package hellojpa;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityMappingTest {
  static EntityManagerFactory emf;

  EntityManager em;

  @BeforeAll
  static void setUpAll() {
    emf = Persistence.createEntityManagerFactory("hello");
  }

  @AfterAll
  static void closeAll() {
    emf.close();
  }

  @BeforeEach
  void setUpEach() {
    em = emf.createEntityManager();
    em.getTransaction().begin();
  }

  @AfterEach
  void closeEach() {
    em.close();
    em.getTransaction().commit();
  }

  private Member createMember(long id) {
    return Member.builder().age(30).id(id).email("sstest@").name("sso").build();
  }

  @Test
  public void test() {
    System.out.println("before");
    final Member member = createMember(101L);
    em.persist(member);
    System.out.println("after");

    Member find1 = em.find(Member.class, 101L);
    System.out.println(find1);
  }
}
