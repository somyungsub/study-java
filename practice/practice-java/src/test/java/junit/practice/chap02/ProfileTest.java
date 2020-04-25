package junit.practice.chap02;

import org.junit.jupiter.api.Test;

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

}