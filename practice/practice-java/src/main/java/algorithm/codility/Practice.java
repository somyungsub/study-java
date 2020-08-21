package algorithm.codility;

import java.util.HashMap;
import java.util.Map;

public class Practice {

  public int test1(String S) {

    String[] check = {"B", "A", "L", "L", "O", "O", "N"};
    int count = 0;

    while (isEnable(S, check)){
      String tmp = S;
      for (int i = 0; i < check.length; i++) {
        String s = check[i];
        for(int j = 0; j<S.length(); j++){
          String c = String.valueOf(S.charAt(j));
          if (s.equals(c)) {
            tmp = tmp.replaceFirst(c, "");
            break;
          }
        }
      }

      S = tmp;
      count++;
    }

    return count;
  }

  private boolean isEnable(String S, String[] check) {
    Map<String, Integer> map = new HashMap<>();
    Map<String, Integer> compare = new HashMap<>();

    if (S.isEmpty()) {
      return false;
    }

    for (int i = 0; i < check.length; i++) {
      String key = check[i];
      map.put(key, map.getOrDefault(key, 0) + 1);
    }

    for (int i = 0; i < S.length(); i++) {
      String c = String.valueOf(S.charAt(i));
      compare.put(c, compare.getOrDefault(c, 0) + 1);
    }

    int count = 0;
    for (String key : map.keySet()) {
      Integer compareValue = compare.get(key);
      Integer checkValue = map.get(key);
      if (compareValue != null && compareValue >= checkValue) {
        count += compareValue;
      }
    }

    return count >= 7;
  }

  public String test2(String S) {

//    Map<String, Integer> map = new HashMap<>();
//
//    for (int i = 0; i < S.length(); i++) {
//      String key = String.valueOf(S.charAt(i));
//      map.put(key, map.getOrDefault(key, 0) + 1);
//    }
//
//    int max = 0;
//    String result = "";
//    for (String key : map.keySet()) {
//      Integer value = map.get(key);
//      if (value > max) {
//        max = value;
//        result = key;
//      }
//    }
//
//    return result;

    int[] occurrences = new int[S.length()];
    Map<String, Integer> map = new HashMap<>();

    for (char ch : S.toCharArray()) {
//      occurrences[ch - 'a']++;
      map.put(String.valueOf(ch), map.getOrDefault(String.valueOf(ch), 0) + 1);
    }

    System.out.println("map = " + map);
//    for (int i = 0; i < S.length(); i++) {
//      map.put(String.valueOf(S.charAt(i)), map.getOrDefault(String.valueOf(S.charAt(i)), 0) + 1);
//    }

    char best_char = 'a';
    int best_res = 0;

    for (String key : map.keySet()) {
      if (map.get(key) >= best_res) {
        best_char = key.charAt(0);
        best_res = map.get(key);
      }
    }
//    for (int i = 1; i < S.length(); i++) {
//      if (occurrences[i] >= best_res) {
//        best_char = (char) ((int) 'a' + i);
//        best_res = occurrences[i];
//      }
//    }
    return Character.toString(best_char);
  }
}
