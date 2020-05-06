package practice.jpashop.jpql;

import org.junit.jupiter.api.*;
import practice.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

class JpqlTest {
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

  @Test
  @DisplayName("JPQL 기초")
  public void jpql() {
    final List<Member> resultList = em.createQuery(
            "select m from Member m where m.username like '%kim%'", Member.class
    ).getResultList();

    System.out.println("resultList = " + resultList);
  }

  @Test
  @DisplayName("Criteria 기초")
  public void criteria() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    final CriteriaQuery<Member> query = cb.createQuery(Member.class);
    final Root<Member> from = query.from(Member.class);
    final CriteriaQuery<Member> cq = query.select(from).where(cb.equal(from.get("username"), "kim"));
    final List<Member> resultList = em.createQuery(cq).getResultList();
    System.out.println("resultList = " + resultList);
  }


  @Test
  @DisplayName("QueryDSL 기초")
  public void queryDSL() {

  }
  @Test
  @DisplayName("Native Query 기초")
  public void nativeQuery() {
    final List resultList = em.createNativeQuery("select member_id, city, street from member").getResultList();
    System.out.println("resultList = " + resultList);
  }

  @Test
  @DisplayName("Spring JDBC, JDBC 직접 작성시")
  public void jdbc() {
    System.out.println("==== 수동 플러시 시점 필요 ====");
    System.out.println("JDBC는 JPA가 아니므로 별개의 영역으로 관리 됨 -> em.flush ");
  }

}
