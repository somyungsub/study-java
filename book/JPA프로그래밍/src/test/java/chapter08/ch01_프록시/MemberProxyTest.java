package chapter08.ch01_프록시;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.hamcrest.MatcherAssert.assertThat;


public class MemberProxyTest {
  @Test
  public void Member프록시() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpabook");
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    final Member member = entityManager.getReference(Member.class, "id1");
//    assertThat(member.getUsername(), CoreMatchers.is("1"));
    
  }

}