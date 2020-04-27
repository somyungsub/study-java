package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
  public static void main(String[] args) {
    System.out.println("~Hello JPA~");

    // EM 생성
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    final EntityManager em = emf.createEntityManager();

    // Transaction 설정 매우 중요!
    final EntityTransaction tx = em.getTransaction();
    tx.begin();

    // Code
    try {
        // save
//      final Member member = new Member();
//      member.setId(1L);
//      member.setName("ss2");
//      member.setAge(32);
//      member.setEmail("ss2@ss");
//      em.persist(member);
//      tx.commit();

      // select
      final Member member = em.find(Member.class, 1L);// select
//      System.out.println("member = " + member);

      // remove
//      em.remove(member);

      // update
//      member.setName("update name");
//      tx.commit();

      // JPQL : Member class 객체를 대상으로 쿼리를 만듬
      final List<Member> memberList =
              em.createQuery("select m from Member as m", Member.class)
                      .setFirstResult(1)
                      .setMaxResults(8)
                      .getResultList();
      memberList.forEach(System.out::println);


    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      // 자원 반환
      em.close();
    }



    emf.close();
  }
}
