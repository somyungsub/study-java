package algorithm.progammers.kit;

import java.util.*;
import java.util.stream.Collectors;

public class KitHash {

  public String notComplete(String[] participant, String[] completion) {
    String answer = "";
    Map<String, Integer> map = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();

    for (String name : participant) {
      if (map.containsKey(name)) {
        map.put(name, map.get(name) + 1);
      } else {
        map.put(name, 1);
      }
    }

    for (String name : completion) {
      if (map2.containsKey(name)) {
        map2.put(name, map2.get(name) + 1);
      } else {
        map2.put(name, 1);
      }
    }


    for (String key : map.keySet()) {
      if (!map2.containsKey(key) || !map2.get(key).equals(map.get(key))) {
        answer = key;
        break;
      }
    }

    return answer;

  }

  public String notComplete2(String[] participant, String[] completion) {
    String answer = "";
    HashMap<String, Integer> hm = new HashMap<>();
    for (String player : participant){
      hm.put(player, hm.getOrDefault(player, 0) + 1);
    }


    for (String player : completion){
      hm.put(player, hm.get(player) - 1);
    }

    for (String key : hm.keySet()) {
      if (hm.get(key) != 0){
        answer = key;
        break;
      }
    }

    return answer;
  }


  public boolean telephoneNumber(String[] phone_book) {
    boolean answer = true;

    for (int i = 0; i < phone_book.length; i++) {
      String number = phone_book[i];

      for (int j = 0; j < phone_book.length; j++) {
        String number2 = phone_book[j];
        if (i != j && number2.startsWith(number)) {
          return false;
        }
      }
    }

    return true;
  }

  public boolean telephoneNumberHash(String[] phone_book) {
    boolean answer = true;


    return true;
  }

  public int clothesNumber(String[][] clothes) {
    int answer = 0;

//    Map<String, List<Clothes>> groupingClothes = Arrays.stream(clothes)
//        .map(strings -> new Clothes(strings[0], strings[1]))
//        .collect(Collectors.groupingBy(Clothes::getClothType));

//    for (List<Clothes> value : groupingClothes.values()) {
//      answer += value.size();
//    }

    Map<String, List<Clothes>> clothMap = Arrays.stream(clothes)
        .map(strings -> new Clothes(strings[0], strings[1]))
        .collect(Collectors.groupingBy(Clothes::getClothType));

    System.out.println("clothMap = " + clothMap);

//    for (int i = 0; i < clothes.length; i++) {
//      String type = clothes[i][1];
//      List<String[]> strings = clothMap.get(type);
//
//      for (int j = i + 1; j < clothes.length; j++) {
//        String type2 = clothes[j][1];
//        clothMap.get(type2);
//      }
//
//    }

//    for (List<String[]> value : clothMap.values()) {
//      answer += value.size();
//    }
//
//    if (clothMap.size() > 1) {
//      int asInt = clothMap.values().stream()
//          .mapToInt(value -> value.size())
//          .reduce((left, right) -> left * right).getAsInt();
//
//      answer += asInt;
//    }

    return answer;
  }

  private class Clothes {

    private final String clothName;
    private final String clothType;

    public Clothes(String clothName, String clothType) {
      this.clothName = clothName;
      this.clothType = clothType;
    }

    public String getClothType() {
      return clothType;
    }
  }
}
