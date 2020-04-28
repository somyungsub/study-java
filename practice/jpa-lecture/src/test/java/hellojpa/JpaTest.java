package hellojpa;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

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
    final Member member = Member.builder().age(30).id(100L).email("sstest@").name("sso").build();
    em.persist(member);
    System.out.println("after");

    Member find1 = em.find(Member.class, 100L);
    Member find2 = em.find(Member.class, 100L);

    System.out.println("find1 = " + find1);
    System.out.println("find2 = " + find2);

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
    final Member member = Member.builder().age(30).id(101L).email("sstest@").name("sso").build();
    final Member member2 = Member.builder().age(30).id(102L).email("sstest@").name("sso").build();
    final Member member3 = Member.builder().age(30).id(103L).email("sstest@").name("sso").build();

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

}