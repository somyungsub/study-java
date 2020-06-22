package outs.fruit;

import java.util.Scanner;

/*
  프로그램 흐름에 필요한 출력문을 관린하는 클래스(객체)
 */
public class FruitPrint {

  private FruitStore store; // 과일상점
  private Scanner scanner;  // 입력

  public FruitPrint(FruitStore store) {
    this.store = store;
    this.scanner = new Scanner(System.in);
  }

  // 전체목록 출력
  public void printList() {
    System.out.println("1. 매출");
    System.out.println("2. 매입");
    System.out.println("3. 재고");
    System.out.println("4. 수익현황");
    System.out.println("5. 환불");
    System.out.println("6. 종료");
    System.out.print("항목을 선택하세요 : ");
  }

  // 선택 목록 출력
  public void printSelect(int select) {
    System.out.println("===============================");
    String name = "";
    int count = 0;
    switch (select) {
      case 1:   // 매출
        store.printFruitList();
        System.out.println("===============================");
        System.out.println("판매하실 상품명과 개수를 입력하세요");
        System.out.print("상품명 : ");
        name = scanner.next();

        System.out.print("개수 : ");
        count = scanner.nextInt();

        System.out.println("===============================");
        boolean isSell = store.sellFruit(name, count);

        // 판매불가인 경우 매출 재호출
        if (!isSell) {
          printSelect(1);
        }
        break;
      case 2:   // 매입
        store.printFruitList();
        System.out.println("===============================");
        System.out.println("구매하실 상품명과 개수를 입력하세요");
        System.out.print("상품명 : ");
        name = scanner.next();

        System.out.print("개수 : ");
        count = scanner.nextInt();

        System.out.println("===============================");
        store.buyFruit(name, count);
        break;
      case 3:   // 재고
        store.printFruitStock();
        break;
      case 4:   // 수익현황
        store.printIncomeList();
        break;
      case 5:   // 환불
        store.printFruitList();
        System.out.println("===============================");
        System.out.println("환불하실 상품명과 개수를 입력하세요");
        System.out.print("상품명 : ");
        name = scanner.next();

        System.out.print("개수 : ");
        count = scanner.nextInt();

        System.out.println("===============================");
        store.buyFruit(name, count);
        break;
      case 6:   // 종료
        System.out.println("이용해 주셔서 감사합니다");
        break;
      default:
        System.out.println("잘못된 입력입니다.");
        break;
    }

    System.out.println("===============================");
  }


}
