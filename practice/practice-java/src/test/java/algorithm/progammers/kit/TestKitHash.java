package algorithm.progammers.kit;

import algorithm.progammers.kit.KitHash;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestKitHash {

  KitHash hash = new KitHash();

  @Test
  @DisplayName("완주하지 못한 선수")
  public void test() {
    String[] participant = {"leo", "kiki", "eden"};
    String[] completion = {"kiki", "eden"};
    System.out.println(hash.notComplete(participant, completion));
    System.out.println(hash.notComplete2(participant, completion));
  }

  @Test
  @DisplayName("완주하지 못한 선수")
  public void test2() {
    String[] participant = {"mislav", "stanko", "mislav", "ana"};
    String[] completion = {"stanko", "ana", "mislav"};
    System.out.println(hash.notComplete(participant, completion));
    System.out.println(hash.notComplete2(participant, completion));
  }

  @Test
  @DisplayName("전화번호 목록")
  public void test3() {
    String[] phoneNumber = {"119", "97674223", "1195524421"};
    System.out.println(hash.telephoneNumber(phoneNumber));

    String[] phoneNumber2 = {"123", "456", "789"};
    System.out.println(hash.telephoneNumber(phoneNumber2));

    String[] phoneNumber3 = {"12", "123", "1235", "567", "88"};
    System.out.println(hash.telephoneNumber(phoneNumber3));
  }

  @Test
  @DisplayName("위장")
  public void test4() {
    String[][] clothes = {
        {"yellow_hat", "headgear"},
        {"blue_sunglasses", "eyewear"},
        {"green_turban", "headgear"}
    };

    System.out.println(hash.clothesNumber(clothes));

    String[][] clothes2 = {
        {"crow_mask", "face"},
        {"blue_sunglasses", "face"},
        {"smoky_makeup", "face"}
    };

    System.out.println(hash.clothesNumber(clothes2));
  }

}