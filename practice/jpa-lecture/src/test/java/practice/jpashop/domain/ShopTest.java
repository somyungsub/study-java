package practice.jpashop.domain;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

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
    final Long memberId = order.getMember().getId();

    final Member member = em.find(Member.class, memberId);
  }

  @Test
  public void test3() {
    final Team teamA = Team.builder().name("TeamA").build();
    em.persist(teamA);

    final Member member1 = Member.builder()
            .username("member1")
            .team(teamA)
            .build();
    em.persist(member1);

    System.out.println("teamA = " + teamA);
    System.out.println("member1 = " + member1);

    em.flush();
    em.clear();
    System.out.println("====================");

    final Member member = em.find(Member.class, member1.getId());
    System.out.println("member = " + member);
  }

  @Test
  @DisplayName("단방향 연관관계")
  public void test4() {
    final Team teamA = Team.builder().name("TeamB").build();
    em.persist(teamA);

    em.flush();
    em.clear();
    System.out.println("====================");

    final Member member = em.find(Member.class, 3L);
    System.out.println("member = " + member);

    // update
    final Team team = em.find(Team.class, 5L);
    member.setTeam(team);

    System.out.println("member = " + member);
  }

  @Test
  @DisplayName("양방향 연관관계")
  public void test5() {
    final Team teamA = Team.builder().name("TeamB").build();
    em.persist(teamA);
    final Member member1 = Member.builder().team(teamA).username("ss1").build();
    em.persist(member1);;
    final Member member2 = Member.builder().team(teamA).username("ss2").build();
    em.persist(member2);
    final Member member3 = Member.builder().team(teamA).username("ss3").build();
    em.persist(member3);

    em.flush();
    em.clear();
    System.out.println("====================");

    final Member member = em.find(Member.class, member1.getId());
    final List<Member> members = member.getTeam().getMembers();

    members.forEach(m -> System.out.println("m : " + m.getUsername()));
    System.out.println("members = " + member.getTeam());
  }

  @Test
  @DisplayName("양방향 연관관계")
  public void test6() {
    final Team teamB = new Team();
    teamB.setName("TeamB");
    System.out.println("teamB = " + teamB);
    em.persist(teamB);

    final Member member1 = Member.builder().username("ss1").build().addTeam(teamB);
    final Member member2 = Member.builder().username("ss1").build().addTeam(teamB);
    final Member member3 = Member.builder().username("ss1").build().addTeam(teamB);

    em.persist(member1);
    em.persist(member2);
    em.persist(member3);

//    em.flush();
//    em.clear();
    System.out.println("====================");

    final Member member = em.find(Member.class, member1.getId());
    final List<Member> members = member.getTeam().getMembers();

    members.forEach(m -> System.out.println("m : " + m.getUsername()));
//    System.out.println("members = " + member.getTeam());
  }



}