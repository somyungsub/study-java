package junit.practice.chap02;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

  @Test
  public void test() {
    Profile profile = new Profile("Bull Hockey, Inc.");
    Question question = new BooleanQuestion(1, "Got bonuses?");
    Criteria criteria = new Criteria();
    Answer answer = new Answer(question, Bool.TRUE);
    Criterion criterion = new Criterion(answer, Weight.MustMatch);

    criteria.add(criterion);
  }

  @Test
  public void test2() {
    assertThat(new String[]{"A", "B"}, equalTo(new String[]{"A", "B"}));
  }

  @Test
  public void test3() {

//    assertThat(2.32 * 3, equalTo(6.96));
    assertTrue(Math.abs(2.32 * 3) - 6.96 < 0.0005);
  }

  @Test
  public void test4() {

  }

}