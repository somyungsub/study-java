package chap05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*
  문자열 연결 연산 내부에 Iterator 숨겨진 상황 -> 이런코드 금물
 */
public class HiddenIterator {

  private final Set<Integer> set = new HashSet<>(); // 비권장
  private final Set<Integer> set2 = Collections.synchronizedSet(new HashSet<>());  // 권장

  public synchronized void add(Integer i) {
    set.add(i);
  }

  public synchronized void addTenThings() {
    Random r = new Random();
    for (int i = 0; i < 10; i++) {
      add(r.nextInt());
    }

    System.out.println("DEBUG : added ten elements to" + set);
  }
}
