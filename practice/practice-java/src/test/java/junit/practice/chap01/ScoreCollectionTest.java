package junit.practice.chap01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ScoreCollectionTest {


  @Test
  @DisplayName("2개인자 테스트")
  public void answerArithmeticMeanOfTwoNumbers() {

    // 준비
    ScoreCollection collection = new ScoreCollection();
    collection.add(() -> 5);
    collection.add(() -> 7);

    // 실행
    int actualResult = collection.arithmeticMean();

    // 확인
    assertThat(actualResult, equalTo(5));
  }
}