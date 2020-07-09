package chap04;

import java.util.HashSet;
import java.util.Set;



/*
 Thread Safe
 - 다양한 기법을 통해 스레드 안정성 확보
 - 데이터 캡슐화, synchronized
 */

public class PersonSet {
  private final Set<Person> mySet = new HashSet<>();

  public synchronized void addPerson(Person person) {
    mySet.add(person);
  }

  public synchronized boolean containsPerson(Person p) {
    return mySet.contains(p);
  }

  private class Person {
  }
}
