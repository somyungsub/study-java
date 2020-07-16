package collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollectionTest {

  @Test
  @DisplayName("HashMap")
  public void map(){
    Map<Integer, DataTest> map = getMap();

    DataTest dataTest = map.get(1);

    dataTest.setNum(4);
    dataTest.setName("444");

    System.out.println("map = " + map);
  }

  @Test
  @DisplayName("가변")
  public void 가변(){
    Map<Integer, DataTest> map = getMap();
    HashMap<Integer, DataTest> immutable = new HashMap<>(map);

    DataTest dataTest = immutable.get(1);

    dataTest.setNum(4);
    dataTest.setName("444");

    System.out.println("map = " + map);
    System.out.println("immutable = " + immutable);
  }

  @Test
  @DisplayName("불변")
  public void 불변(){
    Map<Integer, DataImmutableTest> immutableMap = getImmutableMap();
    DataImmutableTest dataImmutableTest = immutableMap.get(1);

    System.out.println("immutableMap = " + immutableMap);
    System.out.println("dataImmutableTest = " + dataImmutableTest);

  }

  @Test
  @DisplayName("리스트확인")
  public void list() {
    Map<Integer, DataTest> map = getMap();
    Collection<DataTest> values = map.values();

    System.out.println("values = " + values);

    ArrayList<DataTest> dataTests = new ArrayList<>(values);
    dataTests.get(0).setNum(5); // 변경됨

    System.out.println("values2 = " + values);

  }

  private Map<Integer, DataTest> getMap() {
    Map<Integer, DataTest> map = new HashMap<>();

    map.put(1, new DataTest(1,"11"));
    map.put(2, new DataTest(2,"22"));
    map.put(3, new DataTest(3,"33"));

    return map;
  }

  private Map<Integer, DataImmutableTest> getImmutableMap() {
    Map<Integer, DataImmutableTest> map = new HashMap<>();

    map.put(1, new DataImmutableTest(1,"11"));
    map.put(2, new DataImmutableTest(2,"22"));
    map.put(3, new DataImmutableTest(3,"33"));

    return map;
  }

  @Data
  @AllArgsConstructor
  class DataTest {

    private int num;
    private String name;

    @Override
    public String toString() {
      return "DataTest{" +
          "num=" + num +
          ", name='" + name + '\'' +
          '}';
    }
  }

  @Data
  @AllArgsConstructor
  class DataImmutableTest {

    // 불변객체로 만들어야 가능
    private final int num;
    private final String name;

    @Override
    public String toString() {
      return "DataTest{" +
          "num=" + num +
          ", name='" + name + '\'' +
          '}';
    }
  }

}
