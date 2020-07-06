package chap03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapImmutable {
    private final  Map<Integer, Integer> map = new HashMap<>();
//    private volatile  Map<Integer, Integer> map = new HashMap<>();
//  private final Map<Integer, Integer> map = new ConcurrentHashMap<>();
  public void add(Integer i) {
    map.put(i, i);
  }

  public Map<Integer, Integer> getMap() {
    return map;
  }
}

