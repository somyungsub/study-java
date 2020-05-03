package practice.jpashop.domain;

import org.hibernate.Hibernate;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import practice.jpashop.extend.Book;
import practice.jpashop.extend.Movie;

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
  void beforeEachSetup() {
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
    em.persist(member1);
    ;
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

  @DisplayName("insert - book item")
  @ParameterizedTest(name = "[{index}] : {displayName}")
  @CsvSource(value = {
          "12000, JPA, 김연한",
          "10000, JPA2, ssosso"
  })
  public void insert_book(String price, String userName, String bookName) {

    BookItem book = new BookItem();

    book.setAuthor(userName);
    book.setName(bookName);
    book.setPrice(Integer.parseInt(price));

    em.persist(book);

  }

  @Test
  @DisplayName("프록시")
  public void proxy() {
    Member member = new Member();
    member.setUsername("hello");

    em.persist(member);

    em.flush();
    em.clear();

    final Member member1 = em.find(Member.class, 1L); // 셀렉트 쿼리실행됨
//    System.out.println("member1 = " + member1);
  }

  @Test
  @DisplayName("프록시 - getRef 메서드")
  public void proxy_ref() {
    Member member = new Member();
    member.setUsername("hello");

    em.persist(member);

    em.flush();
    em.clear();

    final Member member1 = em.getReference(Member.class, 1L); // 셀렉트 쿼리실행 안됨, Proxy 객체를 들고 옴
    System.out.println(member1.getClass());  // 클래스 정보 확인 :Member$HibernateProxy$Bk0fRyKl
//    System.out.println("member1 = " + member1);
  }

  @Test
  @DisplayName("프록시 - getRef 메서드2")
  public void proxy_ref2() {
    Member member = new Member();
    member.setUsername("hello");

    em.persist(member);

    em.flush();
    em.clear();

    final Member member1 = em.getReference(Member.class, 1L); // 셀렉트 쿼리실행 안됨, Proxy 객체를 들고 옴
    System.out.println(member1.getClass());  // 클래스 정보 확인 :Member$HibernateProxy$Bk0fRyKl
    System.out.println("member1.getUsername() = " + member1.getUsername());
    System.out.println("member1 = " + member1.getClass());
//    System.out.println("member1 = " + member1);
  }

  @Test
  @DisplayName("프록시 - getRef 메서드3")
  public void proxy_ref3() {
    Member member1 = new Member();
    member1.setUsername("hello1");
    Member member2 = new Member();
    member2.setUsername("hello2");

    em.persist(member1);
    em.persist(member2);

    em.flush();
    em.clear();

    final Member findMember1 = em.find(Member.class, member1.getId());
    final Member findMember2 = em.getReference(Member.class, member2.getId());

    System.out.println(findMember1 == findMember2);
    System.out.println(findMember1.getClass() == findMember2.getClass());
    System.out.println(findMember1 instanceof Member);
    System.out.println(findMember2 instanceof Member);
//    System.out.println(findMember1 instanceof findMember2);
  }
  @Test
  @DisplayName("프록시 - getRef 메서드4")
  public void proxy_ref4() {
    Member member1 = new Member();
    member1.setUsername("hello1");

    em.persist(member1);

    em.flush();
    em.clear();

    final Member findMember1 = em.find(Member.class, member1.getId());
    System.out.println("findMember1 = " + findMember1.getClass());

    /*
      proxy가 아니라 member 타입이 나옴 : 이유는 find를 통해 영속성 컨텍스트에 로딩이 되어 있으므로 getRef를 사용해도 이 인스턴스를 반환하게 됨
     */
    final Member reference = em.getReference(Member.class, member1.getId());
    System.out.println("reference = " + reference.getClass());

    System.out.println(findMember1 == reference);                       // 따라서 같은 타입 비교가 true 됨
    System.out.println(findMember1.getClass() == reference.getClass()); // 따라서 같은 타입 비교가 true 됨
    System.out.println(findMember1 instanceof Member);
    System.out.println(reference instanceof Member);

  }
  @Test
  @DisplayName("프록시 - getRef 메서드5")
  public void proxy_ref5() {
    Member member1 = new Member();
    member1.setUsername("hello1");

    em.persist(member1);

    em.flush();
    em.clear();

    final Member refMember = em.getReference(Member.class, member1.getId());
    System.out.println("refMember = " + refMember.getClass());    // proxy
    System.out.println("프록시 사용 -> 초기화 : " + refMember.getUsername());

    final Member findMember = em.find(Member.class, member1.getId());
    System.out.println("findMember = " + findMember.getClass());  // Member -> Proxy 반환을 함... find를 해도 proxy 객체를 반환하게 됨

    System.out.println(refMember == findMember);  // 어떻게든 JPA에서는 타입을 맞추고자 함
    System.out.println(refMember.getClass() == findMember.getClass());
    System.out.println(refMember instanceof Member);
    System.out.println(findMember instanceof Member);

  }
  @Test
  @DisplayName("프록시 - getRef 메서드6")
  public void proxy_ref6() {
    Member member1 = new Member();
    member1.setUsername("hello1");

    em.persist(member1);

    em.flush();
    em.clear();

    final Member refMember = em.getReference(Member.class, member1.getId());
    System.out.println("refMember = " + refMember.getClass());    // proxy

    // 준영속성 상태
    em.detach(refMember);

    // 영속성 초기화
//    em.clear();

    /*
      준영속성, clear(초기화)인 경우 예외 발생 : msg [no Session]
      refMember 를 사용하려고 하면 -> 관리 대상이 아니므로 영속성 컨텍스트 내에서 사용하는 기숟를을 사용 할 수가 없게 되므로... 예외처리 걸림
     */
    System.out.println(refMember);
    System.out.println("refMember.getUsername() = " + refMember.getUsername());
  }

  @Test
  @DisplayName("프록시 - getRef 메서드7")
  public void proxy_ref7() {
    Member member1 = new Member();
    member1.setUsername("hello1");

    em.persist(member1);

    em.flush();
    em.clear();

    final Member refMember = em.getReference(Member.class, member1.getId());
    System.out.println("refMember = " + refMember.getClass());    // proxy

    System.out.println("refMember = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

//    System.out.println("refMember.getUsername() = " + refMember.getUsername()); // 강제 초기화

    Hibernate.initialize(refMember);  // 강제 초기화
    System.out.println("refMember = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

  }

  @Test
  @DisplayName("즉시로딩과 지연로딩")
  public void lazy() {
    Member member1 = new Member();
    member1.setUsername("hello1");

    Team team = new Team();
    team.setName("teamA");

    member1.setTeam(team);

    em.persist(member1);
    em.persist(team);

    em.flush();
    em.clear();

    // team 조인을 안함, 사용시점에 조인 실행 (지연로딩)
    final Member findMember = em.find(Member.class, member1.getId());
    System.out.println(findMember.getTeam().getClass());  // Proxy 객체 확인

    // 사용시점 sql 실행
    System.out.println("=================================================");
    System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());
    System.out.println("=================================================");
  }

  @Test
  @DisplayName("즉시로딩과 지연로딩 N+1문제 - EAGER 설정 후 JPQL")
  public void eager() {
    for (int i = 0; i < 5; i++) {
      Member member1 = new Member();
      member1.setUsername("hello1");

      Team team = new Team();
      team.setName("teamA");

      member1.setTeam(team);

      em.persist(team);
      em.persist(member1);

    }

    em.flush();
    em.clear();
    System.out.println("============================================================");

    /*
      JPQL -> sql 쿼리 문 2번 실행 즉시로딩해도...
      - SQL로 번역 후
        1. Member 갖고오고 -> 즉시로딩 확인 후
        2. Team을 갖고오기 위해 Team row 갯수 만큼 별도로 Select 실행 하여 갖고옴...
        3. 성능 최악됨
     */
    final List<Member> list = em.createQuery("select m from Member m", Member.class).getResultList();

    list.forEach(System.out::println);

    /*
       해결방안
       1. 지연로딩 전부 섲렁
       2. 패치조인을 통해 전략 설정
     */
  }
  @Test
  @DisplayName("즉시로딩과 지연로딩 N+1문제 - 해결방안 fetchjoin")
  public void eager_fetchjoin() {
    for (int i = 0; i < 5; i++) {
      Member member1 = new Member();
      member1.setUsername("hello1");

      Team team = new Team();
      team.setName("teamA");

      member1.setTeam(team);

      em.persist(team);
      em.persist(member1);

    }

    em.flush();
    em.clear();
    System.out.println("============================================================");

    /*
       N+1문제 해결방안
       1. 지연로딩 전부 섲렁
       2. 패치조인을 통해 전략 설정
     */
    final List<Member> list = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

    list.forEach(System.out::println);

  }

  @Test
  @DisplayName("영속성 전이 - ")
  public void persist_test() {
    Child child1 = new Child();
    Child child2 = new Child();

    Parent parent = new Parent();
    parent.addChild(child1);
    parent.addChild(child2);

    em.persist(parent);
    // CASCADE 를 통해 영속성을 전이함
//    em.persist(child1);
//    em.persist(child2);
  }

  @Test
  @DisplayName("고아객체")
  public void orphan() {
    Child child1 = new Child();
    Child child2 = new Child();

    Parent parent = new Parent();
    parent.addChild(child1);
    parent.addChild(child2);

    em.persist(parent);

    em.flush();
    em.clear();

    final Parent parent1 = em.find(Parent.class, parent.getId());

    // 자식 객체 제거 -> DB에서도 제거됨. orphanRemoval = true 인경우
//    parent1.getChildList().remove(0);

    // -> 자식객체 다 삭제됨
    em.remove(parent1);
  }
}