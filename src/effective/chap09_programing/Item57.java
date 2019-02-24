package effective.chap09_programing;


import java.util.Iterator;
import java.util.List;

/*
  57. 지역변수의 범위를 최소화하라
   - 메서드를 작게 유지하고 한가지 기능에 집중하게 해줌
 */
public class Item57 {
  public static void main(String[] args) {

    List<String> list = List.of("A", "B", "d");

    // 1. for문 (권장)
    for (Iterator<String> i = list.iterator(); i.hasNext();) {
      System.out.println(i.next());
    }

    System.out.println("=================");
    // 2. while문
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    // 버그 발생 원인 - 복붙시 실수
    Iterator<String> it2 = list.iterator();
    while (it.hasNext()) {  // 실수 포인트!
      System.out.println(it2.next());

    }


    // i-> i2로 바꾸는 순간 복붙 시 변수가 쓰이지 않는 부분의 오류를 잡아 줄 수 있다.
    for (Iterator<String> i = list.iterator(); i.hasNext();) {
      System.out.println(i.next());
    }

    // 한계값을 변수n에 저장하여 활용하여 반복시 마다 다시 계산해야하는 비용을 없앴다
    for (int i = 0, n = 100; i < n; i++) {
      System.out.println("i = " + i);
    }
  }
}
