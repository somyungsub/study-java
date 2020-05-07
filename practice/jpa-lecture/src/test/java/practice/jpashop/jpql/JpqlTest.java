package practice.jpashop.jpql;

import jpql.MemberJpql;
import jpql.ProductJpql;
import jpql.TeamJpql;
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


  @Test
  @DisplayName("paging ")
  public void paging() {

    for (int i = 0; i < 100; i++) {
      MemberJpql memberJpql = new MemberJpql();
      memberJpql.setUsername("sss");
      memberJpql.setAge(i);
      em.persist(memberJpql);
    }

    em.flush();
    em.clear();

    final List<MemberJpql> resultList = em.createQuery("select m from MemberJpql m order by m.age desc ", MemberJpql.class)
            .setFirstResult(0)
            .setMaxResults(20)
            .getResultList();

    System.out.println("resultList = " + resultList);
    resultList.forEach(memberJpql1 -> {
      System.out.println("memberJpql1 = " + memberJpql1.getAge());
    });

  }

  @Test
  @DisplayName(" inner join ")
  public void join() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("Team!");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Team!");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);

    ProductJpql productJpql = new ProductJpql();
    productJpql.setName("Team!");

    em.persist(productJpql);
    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    /*
        ** 1:M -> @OneToMany 에 fetch를 Lazy로 잡지 않을 경우 셀렉트문 한번 더 나감 **
        select
            teamjpql0_.TEAM_ID as team_id1_29_0_,
            teamjpql0_.name as name2_29_0_
        from
            TeamJpql teamjpql0_
        where
            teamjpql0_.TEAM_ID=?
     */

//    final String sql = "select m from MemberJpql m inner join m.teamJpql";
//    final String sql = "select m from MemberJpql m inner join m.teamJpql t where t.name = :teamName"; // inner 생략 가능
//    final String sql = "select m from MemberJpql m left outer join m.teamJpql t";         // outer는 생략 가능
//    final String sql = "select m from MemberJpql m, TeamJpql t where m.username = t.name";  // 카티션 곱
//    final String sql = "select m from MemberJpql m left join m.teamJpql t on t.name='Team!' ";  // left 조인 -> on 절 -> and 절
//    final String sql = "select m from MemberJpql m left join TeamJpql  t on t.name='Team!' ";  // left 조인 -> on 절 -> and 절
    final String sql = "select m from MemberJpql m left join ProductJpql t on t.name='Team!' ";  // left 조인 -> on 절 -> and 절


    final List<MemberJpql> resultList = em.createQuery(sql, MemberJpql.class)
//            .setParameter("teamName", "Team!")
            .getResultList();

    System.out.println("resultList = " + resultList.size());

    resultList.forEach(memberJpql1 -> {
      System.out.println("memberJpql1 = " + memberJpql1.getAge());
    });

  }

  @Test
  @DisplayName(" 서브쿼리 ")
  public void sub_query() {

    TeamJpql teamJpql = new TeamJpql();
    teamJpql.setName("Team!");

    MemberJpql memberJpql = new MemberJpql();
    memberJpql.setUsername("Team!");
    memberJpql.setAge(30);
    memberJpql.changeTeam(teamJpql);

    ProductJpql productJpql = new ProductJpql();
    productJpql.setName("Team!");

    em.persist(productJpql);
    em.persist(teamJpql);
    em.persist(memberJpql);

    em.flush();
    em.clear();

    // from  서브쿼리는  안됨 -> 약점, -> 조인으로 풀수 있으면 풀고, 안되면 쿼리 2번 실행 등으로 해결 필요, 네이티브 쿼리 해결
    // 그럼에도 네이티브를 쓰지 않는 이유...
    final String sql = "select (select m from MemberJpql m1) as avgAve from MemberJpql m join TeamJpql t on m.username = 'Team!'";


    final List<MemberJpql> resultList = em.createQuery(sql, MemberJpql.class)
//            .setParameter("teamName", "Team!")
            .getResultList();

    System.out.println("resultList = " + resultList.size());

    resultList.forEach(memberJpql1 -> {
      System.out.println("memberJpql1 = " + memberJpql1.getAge());
    });

  }

}
