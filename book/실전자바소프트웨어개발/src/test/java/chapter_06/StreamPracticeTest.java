package chapter_06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class StreamPracticeTest {

  private StreamPractice streamPractice;

  @BeforeEach
  void setUp() {
    streamPractice = new StreamPractice();
  }

  @Test
  @DisplayName("7.5.4 스트림 - map")
  public void 튜플만들기() {
    Set<String> set = Set.of("a", "b", "c", "d", "e");
    String result = streamPractice.usersTuple(set);
    System.out.println("result = " + result);
  }

}