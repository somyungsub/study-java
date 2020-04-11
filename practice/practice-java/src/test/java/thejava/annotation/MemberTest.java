package thejava.annotation;

import org.junit.Test;
import xunit_tdd.Assert;

public class MemberTest {

  @Test
  public void test() {

    Member member = new Member();
    member.setName("sso");
    member.setAge(33);

    Assert.assertEquals(member.getName(), "sso");

  }

}