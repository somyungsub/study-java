package outs.fruit;

import java.util.Scanner;

public class FruitMain {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    FruitStore store = new FruitStore();        // 과일상점
    FruitPrint print = new FruitPrint(store);   // 과일상점 프로그램 플로우에 필요한 출력문

    while (true) {
      print.printList();              // 1. 메뉴 출력
      int select = scanner.nextInt(); // 2. 메뉴 선택 (매출, 매입, 재고 ...)
      print.printSelect(select);      // 3. 선택된 메뉴에 대해 수행

      // 종료 인 경우
      if (select == 6) {
        return;
      }
    }
  }
}
