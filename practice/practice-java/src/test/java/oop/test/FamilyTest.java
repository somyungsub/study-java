package oop.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class FamilyTest {

  @Test
  @DisplayName("test")
  public void test() {
    Father father = new Family();
    Mother mother = new Family();

    father.sayParent();
    mother.sayParent();
  }

  @Test
  @DisplayName("test2")
  public void test2() {
    List<String> list = List.of("1", "2", "3", "4");

    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next();
      System.out.println("next = " + next);
    }
  }

  @Test
  @DisplayName("test3")
  public void test3() {
    List<String> list = Arrays.asList("1", "2", "3", "4");
    list.add("5");
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next();
      System.out.println("next = " + next);
    }
  }

  @Test
  @DisplayName("에러")
  public void error() {
    for (int i = 0; i < 10; i++) {
      try {
        throw new IOException("i = " + i);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    for (int i = 0; i < 10; i++) {
      System.out.println("마무리 " + i);
    }
  }

  @Test
  @DisplayName("proxy")
  public void proxy() {
//    Family target = new Family();
    Sun target = new Sun();
    Mother o = (Mother) Proxy.newProxyInstance(
        Mother.class.getClassLoader(),
        new Class[]{Mother.class},
        (proxy, method, args) -> {
          System.out.println("Test!!");
      return method.invoke(target, args);
    });

    o.say();
  }

}