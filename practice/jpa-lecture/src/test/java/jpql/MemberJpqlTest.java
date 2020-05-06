package jpql;

import org.junit.jupiter.api.*;
import practice.jpashop.domain.Team;

import javax.persistence.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberJpqlTest {
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
  @DisplayName("테이블 생성")
  public void test() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    em.persist(memberJpql);
  }

  @Test
  @DisplayName("TypeQuery, Query")
  public void test2() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    em.persist(memberJpql);

    TypedQuery<MemberJpql> query = em.createQuery("select m from MemberJpql m", MemberJpql.class);
    TypedQuery<String> query2 = em.createQuery("select m.username from MemberJpql m", String.class);  // 타입정보 받을 수 있을 때
    Query query3 = em.createQuery("select m.username, m.age from MemberJpql m");  // 타입정보 못받음, 복합 타입 셀렉트

  }

  @Test
  @DisplayName("검색 API")
  public void test3() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    em.persist(memberJpql);

    TypedQuery<MemberJpql> query = em.createQuery("select m from MemberJpql m", MemberJpql.class);

    final List<MemberJpql> resultList = query.getResultList();    // 다건

    // Spring Data JPA -> 옵셔널, or Null 반환하도록 되어 있음
    final MemberJpql singleResult = query.getSingleResult();      // 단건

    TypedQuery<MemberJpql> query2 = em.createQuery("select m from MemberJpql m where m.id = 10", MemberJpql.class);
  }

  @Test
  @DisplayName("파라미터 바인딩 - 이름 기반, 권장 ")
  public void test4() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    em.persist(memberJpql);

    final List<MemberJpql> resultList = em.createQuery("select m from MemberJpql m where m.username=:user", MemberJpql.class)
            .setParameter("user", "Ssss")
            .getResultList();
    System.out.println(resultList);
  }

  @Test
  @DisplayName("파라미터 바인딩 - 위치 기반, 비추 -> 버그발생시..디버깅 힘듬")
  public void test5() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    em.persist(memberJpql);

    final List<MemberJpql> resultList = em.createQuery("select m from MemberJpql m where m.username=?1", MemberJpql.class)
            .setParameter(1, "Ssss")
            .getResultList();
    System.out.println(resultList);

  }

  @Test
  @DisplayName("프로젝션 - select ")
  public void test6() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    memberJpql.setAge(10);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    final List<MemberJpql> resultList = em.createQuery("select m from MemberJpql m where m.username=?1", MemberJpql.class)
            .setParameter(1, "Ssss")
            .getResultList();

    final MemberJpql findMember = resultList.get(0);
    findMember.setAge(20);

    System.out.println(resultList);

  }

  @Test
  @DisplayName("프로젝션 - join ")
  public void test7() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    memberJpql.setAge(10);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    System.out.println("=======================================================");

    // 지양
//    final List<TeamJpql> resultList = em.createQuery("select m.teamJpql from MemberJpql m", TeamJpql.class)
//            .getResultList();

    // 가독성으로 인해 지향
    em.createQuery("select t from MemberJpql m join m.teamJpql t", TeamJpql.class).getResultList();

//    final TeamJpql teamJpql = resultList.get(0);
//    System.out.println("teamJpql = " + teamJpql);

    // 임베디드 프로젝션 -> 엔티티에서 시작해야됨 (OrderJpql)
    em.createQuery("select o.addressJpql from OrderJpql o", AddressJpql.class).getResultList();


    // 스칼라 타입 프로젝션
    final List resultList = em.createQuery("select distinct m.username, m.age from MemberJpql m").getResultList();

  }


  @Test
  @DisplayName("프로젝션 - new join ")
  public void test8() {
    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Ssss");
    memberJpql.setAge(10);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    System.out.println("=======================================================");

    List<MemberDto> resultList = em.createQuery("select distinct new jpql.MemberDto(m.username, m.age) from MemberJpql m", MemberDto.class)
            .getResultList();

    System.out.println("resultList = " + resultList);
  }


}