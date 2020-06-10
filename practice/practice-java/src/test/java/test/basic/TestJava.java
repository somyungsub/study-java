package test.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

class TestJava {

  @Test
  @DisplayName("set")
  public void set() {
    Set<String> set = new HashSet<>();
    set.add("b");
    set.add("a");
    set.add("2");
    set.add("d");

    System.out.println("set = " + set);

    Set<String> linked = new LinkedHashSet<>();
    linked.add("b");
    linked.add("a");
    linked.add("2");
    linked.add("d");

    System.out.println("linked = " + linked);


    Set<String> treeSet = new TreeSet<>();
    treeSet.add("b");
    treeSet.add("a");
    treeSet.add("2");
    treeSet.add("d");

    System.out.println("treeSet = " + treeSet);

  }
}
