package algorithm.progammers;

import java.util.*;
import java.util.stream.Collectors;

public class TestKitHash {

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
}
