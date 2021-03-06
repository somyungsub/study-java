package test.collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayTest {

  @Test
  @DisplayName("이진검색")
  public void test() {
    int[] arr = {1, 5, 2, 1, 4, 6, 1, 12, 34};
    int i = Arrays.binarySearch(arr, 5);
    System.out.println("i = " + i);

    int[] arr2 = {1, 4, 5, 9, 10, 12, 15};
    System.out.println(Arrays.binarySearch(arr2, 12));
    System.out.println(Arrays.binarySearch(arr2, 9));
    System.out.println("==========");

    List<Integer> collect = IntStream.of(arr2)
        .mapToObj(Integer::valueOf)
        .collect(Collectors.toList());

    System.out.println(collect.indexOf(12));
    System.out.println(collect.indexOf(9));
  }

  @Test
  @DisplayName("List capacity -> 1.5배씩 늘어남")
  public void test2() throws Exception {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
      list.add(i);
      System.out.format("Size: %2d, Capacity: %2d%n",
          list.size(), getCapacity(list));
    }
  }

  private int getCapacity(ArrayList<?> l) throws Exception {
    Field dataField = ArrayList.class.getDeclaredField("elementData");
    dataField.setAccessible(true);
    return ((Object[]) dataField.get(l)).length;
  }

}
