package chap04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
  메서드 동기화로는 부족한 경우
  -> 락객체를 활용하여 구현
 */
public class ListHelper<E> {

  // 락객체 활용(클라이언트 락)
  public List<E> list = Collections.synchronizedList(new ArrayList<>());

  public boolean putIfAbsent(E e) {
    synchronized (list) {
      boolean absent = !list.contains(e);
      if (absent) {
        list.add(e);
      }
      return absent;
    }
  }

}
