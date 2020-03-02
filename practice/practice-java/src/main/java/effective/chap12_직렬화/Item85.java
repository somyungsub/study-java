package effective.chap12_직렬화;

import java.util.HashSet;
import java.util.Set;


/*
  자바 직렬화 대안을 찾자
  - 직렬화는 위험하니 피해야한다.
  - 안전하지 않은 데이터를 역직렬화 한다면 -> 보안이슈가 발생할 수 있다.
  - 대안으로 JSON(텍스트), 프로토콜 버퍼(이진) 등 사용을 고려
  - 객체 역직렬화 필터링 사용 (java.io.ObjectInputFilter) 정의 -> 역직렬화전 필터링 정의에 의해 검증체크

  객체가 역직렬화 하도록 만들지 말고, 꼭 써야한다면 -> 신경써서 만들어야함
 */
public class Item85 {
  public static void main(String[] args) {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      Set<Object> t1 = new HashSet<>();
      Set<Object> t2 = new HashSet<>();

      t1.add("foo");
      t2.add("foot2");
      s1.add(t1);
      s1.add(t2);

      s2.add(t1);
      s2.add(t2);
      s1 = t1;
      s2 = t2;


    }

  }

}
