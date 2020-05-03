package hellojpa;

import org.junit.jupiter.api.*;
import practice.jpashop.domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

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
    return Member.builder()
            .id(id)
//            .age(30)
            .num(BigDecimal.valueOf(123456789012345.123))
            .email("sstest@")
            .username("sso")
            .createdDate(new Date())
            .lastModifiedDate(new Date())
            .deletedDate(new Date())
            .deletedDate2(LocalDateTime.now())
            .deletedDate3(LocalDate.now())
            .deletedDate4(LocalTime.now())
            .roleType(RoleType.USER)
            .roleType2(RoleType.ADMIN)
            .description("adajdaidjaisigiakdiaskdai")
            .description2(new Byte[]{'h','a','b','a'})
            .description3("hkhkhkhkhk".getBytes())
            .build();
  }

  @Test
  public void test() {
    System.out.println("before");
    final Member member = createMember(100L);
    em.persist(member);
    System.out.println("after");

    Member find1 = em.find(Member.class, 100L);
    System.out.println(find1);
  }

  @Test
  public void test2() {
    System.out.println("before");
    Member find1 = em.find(Member.class, 100L);
    System.out.println(find1);
    final byte[] description3 = find1.getDescription3();
    final String s = Arrays.toString(description3);
    System.out.println(s);
//    for (int i = 0; i < s.length(); i++) {
//      System.out.println(s.charAt(i));
//    }

    for (byte b : description3) {
      System.out.print(Character.toString(b));
    }



    System.out.println("after");
  }
}
