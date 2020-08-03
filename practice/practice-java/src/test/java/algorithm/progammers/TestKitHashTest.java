package algorithm.progammers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

class TestKitHashTest {

  TestKitHash hash = new TestKitHash();

  @Test
  @DisplayName("완주하지 못한 선수")
  public void test(){
    String[] participant = {"leo", "kiki", "eden"};
    String[] completion = {"kiki", "eden"};
    System.out.println(hash.notComplete(participant, completion));
    System.out.println(hash.notComplete2(participant, completion));
  }

  @Test
  @DisplayName("완주하지 못한 선수")
  public void test2(){
    String[] participant = {"mislav", "stanko", "mislav", "ana"};
    String[] completion = {"stanko", "ana", "mislav"};
    System.out.println(hash.notComplete(participant, completion));
    System.out.println(hash.notComplete2(participant, completion));
  }

}