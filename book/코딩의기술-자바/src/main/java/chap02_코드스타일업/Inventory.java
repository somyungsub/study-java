package chap02_코드스타일업;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {
  private final List<Integer> list;

  public Inventory(List<Integer> list) {
    this.list = list;
  }

  void disposeContaminatedSupplies() {
    for (Integer integer : list) {
      if (integer % 6 == 0) {
        System.out.println("integer = " + integer);
        list.remove(integer);
      }
    }
  }

  void disposeContaminatedSupplies_iter() {
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      if (iterator.next() % 6 == 0) {
        System.out.println("iterator = " + iterator);
        iterator.remove();
      }
    }
    list.stream()
      .forEach(System.out::println);
  }
}
