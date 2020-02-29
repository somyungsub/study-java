package design_pattern.kosta.structure.flyweight;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class FlyweightFactory {

  Map<String, Flyweight> pool;  // key - value (객체)

  public FlyweightFactory() {
    this.pool = new TreeMap<>();
  }

  // type별로 Flyweight를 넘겨주는..
  public Flyweight getFlyweight(String key) {
    Flyweight flyweight = pool.putIfAbsent(key, new Flyweight(key));
    System.out.println("flyweight = " + flyweight);
    return pool.get(key);

//    Flyweight flyweight = pool.get(key);
//    return flyweight
  }
}