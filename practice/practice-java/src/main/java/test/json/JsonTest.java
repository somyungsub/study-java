package test.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonTest {

  public void parse(BufferedReader br) {

    StringBuilder sb = new StringBuilder();
    br.lines().forEach(s -> sb.append(s));

    String parseString = sb.toString();

    try {
      JSONParser jsonParser = new JSONParser();
      Object parse = jsonParser.parse(parseString);
      JSONArray jsonArray = (JSONArray) parse;

      Map<String, List<String>> map = getJsonMap(jsonArray);
      System.out.println("map = " + map);

      List<Map.Entry<String, List<String>>> entries = new LinkedList<>(map.entrySet());
      entries.sort((o1, o2) -> o1.getValue().size() < o2.getValue().size() ? 1 : -1);
      entries.sort((o1, o2) -> o2.getKey().compareTo(o1.getKey()));
      System.out.println("entries = " + entries);

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  private Map<String, List<String>> getJsonMap(JSONArray jsonArray) {
    Map<String, List<String>> map = new HashMap<>();

    jsonArray.stream()
        .forEach(o -> {
          JSONObject json = (JSONObject) o;
          Set set = json.keySet();
          for (Object key : set) {
            String o1 = (String)json.get(key);
            if (map.containsKey(key)) {
              List<String> list = map.get(key);
              list.add(o1);
            } else {
              List<String> list = new ArrayList<>();
              list.add(o1);
              map.put((String) key, list);
            }
          }
        });
    return map;
  }
}
