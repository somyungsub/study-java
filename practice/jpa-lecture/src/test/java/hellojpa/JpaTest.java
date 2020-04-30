package hellojpa;

import org.junit.jupiter.api.*;

import javax.persistence.*;
import java.util.List;

class JpaTest {

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
  @DisplayName("영속성 컨텍스트 테스트 [1차캐시] - save")
  public void test_persist_save() {

    System.out.println("before");
    final Member member = createMember(101L);
    em.persist(member);
    System.out.println("after");

    Member find1 = em.find(Member.class, 101L);
    Member find2 = em.find(Member.class, 101L);

    System.out.println("find1 = " + find1);
    System.out.println("find2 = " + find2);

  }

  private Member createMember(long id) {
    return Member.builder().age(30).id(id).email("sstest@").name("sso").build();
  }

  @Test
  @DisplayName("영속성 컨텍스트 테스트 [1차캐시] - find")
  public void test_persist_find() {

    // 1번만 호출됨 1차캐시
    Member find1 = em.find(Member.class, 100L);
    Member find2 = em.find(Member.class, 100L);

  }

  @Test
  @DisplayName("영속성 컨텍스트 테스트 [1차캐시] - find 동일성 보장")
  public void test_persist_find_equals() {

    Member find1 = em.find(Member.class, 100L);
    Member find2 = em.find(Member.class, 100L);

    System.out.println(find1 == find2); // 동일 객체
  }

  @Test
  @DisplayName("영속성 컨텍스트 테스트 [1차캐시] - 지연쓰기")
  public void test_persist_save_lazy() {
    final Member member = createMember(101L);
    final Member member2 = createMember(102L);
    final Member member3 = createMember(103L);

    System.out.println("before");
    em.persist(member);
    em.persist(member2);
    em.persist(member3);
    System.out.println("after");

    // commit 시점에 flush -> 한번에 db 반영
  }

  @Test
  @DisplayName("영속성 컨텍스트 테스트 [더티체킹] - 변경감지")
  public void test_persist_update() {

    final Member member = em.find(Member.class, 101L);
    member.setName("update name test dirty check2");

    System.out.println("before");
    System.out.println("after");

    // commit 시점에 update 실행
  }

  @Test
  @DisplayName("영속성 컨텍스트 테스트 [] - 삭제")
  public void test_persist_delete() {

    final Member member = em.find(Member.class, 101L);
    em.remove(member);

    System.out.println("before");
    System.out.println("after");

    // commit 시점에 delete 실행
  }

  @Test
  @DisplayName("영속성 컨텍스트 Flush")
  public void test_flush() {
    final Member member = createMember(201L);

    em.persist(member);
    em.flush(); // 직접 호출 -> 저장된 SQL 즉시 실행 -> DB와 동기화, commit x

    System.out.println("before");
    System.out.println("after");

    // commit 시점에 -> flush 자동 실행
  }


  @Test
  @DisplayName("영속성 컨텍스트 Flush - JPQL")
  public void test_flush_jpql() {
    final Member member = createMember(501L);
    final Member member2 = createMember(502L);
    final Member member3 = createMember(503L);

    em.persist(member);
    em.persist(member2);
    em.persist(member3);

    System.out.println("before");

    // JPQL 실행 -> 플러시가 자동으로 호출 -> 즉시반영 되는 이유 : 영속된 데이터가 없다면, 조회가 불가하므로
    final TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
    final List<Member> members = query.getResultList();
    members.forEach(System.out::println);

    System.out.println("after");
  }

  @Test
  @DisplayName("영속성 컨텍스트 Flush Mode - commit")
  public void test_flush_commit_mode() {
    final Member member = createMember(601L);
    final Member member2 = createMember(602L);
    final Member member3 = createMember(603L);

    em.persist(member);
    em.persist(member2);
    em.persist(member3);

    // commit 시점에 flush -> JPQL 데이터 출력문 : 601,602,603 데이터 출력안됨 위 테스트랑 다른 이유 [Flush 시점차이]
    // 설정위치 중요 -> 여기까지 persist 된 내용은 commit 시점에 반양하기
    em.setFlushMode(FlushModeType.COMMIT);  // default : FlushModeType.AUTO

    System.out.println("before");

    // JPQL은 바로 실행됨 -> em.persist 실행 시점은 commit 시점에
    final TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
    final List<Member> members = query.getResultList();
    members.forEach(System.out::println);

    System.out.println("after");
  }

  @Test
  @DisplayName("영속성 컨텍스트 Flush Mode - commit2")
  public void test_flush_commit_mode2() {
    final Member member = createMember(701L);
    final Member member2 = createMember(702L);
    final Member member3 = createMember(703L);

    em.persist(member);
    em.persist(member2);
    em.persist(member3);

    System.out.println("before");

    // JPQL은 바로 실행됨 -> em.persist 실행 시점은 commit 시점에
    final TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
    final List<Member> members = query.getResultList();
    members.forEach(System.out::println);

    // 설정 위치에 따라 flush 영역이 달라짐
    em.setFlushMode(FlushModeType.COMMIT);  // default : FlushModeType.AUTO

    System.out.println("after");
  }

  @Test
  @DisplayName("영속성 컨텍스트 준영속화 - detach")
  public void test_detach() {
    final Member member = em.find(Member.class, 100L);
    final Member member2 = em.find(Member.class, 101L);
    final Member member3 = em.find(Member.class, 101L);
    member.setName("준영속 변경");

    System.out.println("before");

    // 준영속화, 컨텍스트 분리, DB 변경확인 -> 반영안됨
    em.detach(member);  // 특정객체만

    final Member detachMember = em.find(Member.class, 100L); // 쿼리 실행여부 확인
    final Member detachMember2 = em.find(Member.class, 101L); // 쿼리 실행여부 확인
    final Member detachMember3 = em.find(Member.class, 102L); // 쿼리 실행여부 확인

    System.out.println("after");
  }
  @Test
  @DisplayName("영속성 컨텍스트 준영속화 - detach")
  public void test_persist2() {
    System.out.println("1");
    final Member member = em.find(Member.class, 100L);
    System.out.println("2");
    final Member member2 = em.find(Member.class, 101L);
    System.out.println("3");
    final Member member3 = em.find(Member.class, 101L);

    System.out.println("4");
    final Member detachMember = em.find(Member.class, 100L); // 쿼리 실행여부 확인

    System.out.println("5");
    final Member detachMember2 = em.find(Member.class, 101L); // 쿼리 실행여부 확인

    System.out.println("6");
    final Member detachMember3 = em.find(Member.class, 102L); // 쿼리 실행여부 확인

    System.out.println("after");
  }

  @Test
  @DisplayName("영속성 컨텍스트 준영속화 - clear")
  public void test_clear() {
    final Member member = em.find(Member.class, 100L);
    member.setName("준영속 변경");

    System.out.println("before");

    // 전체 비움
    em.clear();

    final Member member2 = em.find(Member.class, 100L);

    System.out.println("after");
  }



}