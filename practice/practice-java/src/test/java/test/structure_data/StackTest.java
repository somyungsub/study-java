package test.structure_data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class StackTest {

  @Test
  @DisplayName("스택 기본 메서드")
  public void test1() {
    Stack<Integer> stack = new Stack<>();

    System.out.println("stack.empty() = " + stack.empty());
    System.out.println("stack.isEmpty() = " + stack.isEmpty());


    // push -> 추가
    Integer push = stack.push(1);
    Integer push2 = stack.push(2);
    Integer push3 = stack.push(3);

    System.out.println("push = " + push);
    System.out.println("push2 = " + push2);
    System.out.println("push3 = " + push3);

    System.out.println("stack = " + stack);
    System.out.println("stack.peek() = " + stack.peek());
    System.out.println("stack.size() = " + stack.size());
    System.out.println("stack.capacity() = " + stack.capacity());


    // 추가 : add -> 사이즈 내 인덱스에 추가 삽입 가능
    stack.add(0, 10);
    System.out.println("stack = " + stack);
    System.out.println("stack.size() = " + stack.size());

    stack.add(2, 25);
    System.out.println("stack = " + stack);

    stack.add(50);  // 끝에 삽입
    System.out.println("stack = " + stack);

    System.out.println("stack.empty() = " + stack.empty()); // 비었는지 확인
    System.out.println("stack = " + stack);


    // 데이터 순회 출력
    stack.forEach(o -> System.out.println("o = " + o));
    System.out.println("stack = " + stack);

    stack.add(2);
    System.out.println("stack = " + stack);

    // search (뒤에서 찾은 인덱스 ? 순번 반환)
    int search = stack.search(2);
    int search2 = stack.search(20);
    int search3 = stack.search(25);
    System.out.println("search = " + search);
    System.out.println("search2 = " + search2);
    System.out.println("search3 = " + search3);

    // remove -> 처음 발견된 아이템 제거
    stack.remove(Integer.valueOf(2));
    System.out.println("stack = " + stack);

    stack.remove(Integer.valueOf(3));
    System.out.println("stack = " + stack);

    // remove -> 인덱스 base 삭제
    stack.remove(0);
    System.out.println("stack = " + stack);

    stack.remove(2);
    System.out.println("stack = " + stack);

    // set -> 해당 인덱스 값 데이터 갱신
    stack.set(1, 50);
    System.out.println("stack = " + stack);

    // 맨앞 맨뒤 데이터 확인
    System.out.println("stack.firstElement() = " + stack.firstElement());
    System.out.println("stack.lastElement() = " + stack.lastElement());

    // get(인덱스) -> 데이터 확인
    System.out.println("stack.get(0) = " + stack.get(0));
    System.out.println("stack.get(1) = " + stack.get(1));
    System.out.println("stack.get(2) = " + stack.get(2));
    System.out.println("stack.get(2) = " + stack.get(2));

    // 데이터 존재 확인
    System.out.println("stack.contains(Integer.valueOf(50)) = " + stack.contains(Integer.valueOf(50)));
    System.out.println("stack.contains(Integer.valueOf(55)) = " + stack.contains(Integer.valueOf(55)));

    for (int i = 0; i < 10; i++) {
      stack.add(10 + i);
    }

    System.out.println("stack = " + stack);
    System.out.println("stack.size() = " + stack.size());
    System.out.println("stack.capacity() = " + stack.capacity()); // 10 개단위로 용량 증가


  }
}
