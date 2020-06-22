package outs.fruit;

import java.util.Arrays;

/*
  과일상점에서 필요한 프로그램 관련 로직을 담당하는 클래스(객체)
 */
public class FruitStore {

  private Fruit[] fruits;       // 과일 정보
  private Income[] incomeList;  // 수익 정보

  public FruitStore() {
    incomeList = new Income[0];
    fruits = new Fruit[10];
    fruits[0] = new Fruit("사과", 500, 10);
    fruits[1] = new Fruit("바나나", 400, 10);
    fruits[2] = new Fruit("포도", 500, 10);
    fruits[3] = new Fruit("수박", 1000, 10);
    fruits[4] = new Fruit("메론", 700, 10);
    fruits[5] = new Fruit("키위", 600, 10);
    fruits[6] = new Fruit("복숭아", 500, 10);
    fruits[7] = new Fruit("블루베리", 300, 10);
    fruits[8] = new Fruit("파인애플", 400, 10);
    fruits[9] = new Fruit("망고", 900, 10);
  }

  // 1.매출
  public boolean sellFruit(String name, int count) {
    for (Fruit fruit : fruits) {
      if (name.equals(fruit.getName())) {
        if (fruit.getStock() >= count) {
          // 판매가능
          fruit.setStock(fruit.getStock() - count); // 판매수량 적용
          Income income = new Income(fruit, count, IncomeType.SELLING);
          addIncomeList(income);
          System.out.println(printIncome(income));
          return true;
        } else {
          // 판매불가
          System.out.println(fruit.getName() + "의 재고가 부족합니다");
          System.out.println("재고수 확인후 다시 입력해주세요");
          return false;
        }
      }
    }
    return false;
  }

  // 2.매입, 5.환불 같이 사용
  public void buyFruit(String name, int count) {
    for (Fruit fruit : fruits) {
      if (name.equals(fruit.getName())) {
        fruit.setStock(fruit.getStock() + count); // 매입수량 적용
        Income income = new Income(fruit, count, IncomeType.BUYING);
        addIncomeList(income);
        System.out.println(printIncome(income));
        return;
      }
    }
  }

  // 3. 재고 현황 보여주기
  public void printFruitStock() {
    for (int i = 0; i < fruits.length; i++) {
      System.out.println((i + 1) + ". 상품명 : " + fruits[i].getName() + "/ 재고 : " + fruits[i].getStock() + "개");
    }
  }

  // 4. 수익 현황 보여주기
  public void printIncomeList() {
    int sum = 0;
    for (Income income : incomeList) {
      System.out.println(printIncome(income));
      sum += calcTotalPrice(income);
    }
    System.out.println("총 수익) \t\t" + sum + "원");
  }

  // 현황 보여주기
  public void printFruitList() {
    for (int i = 0; i < fruits.length; i++) {
      System.out.println((i + 1) + ". 상품명 : " + fruits[i].getName() + "/ 가격 : " + fruits[i].getPrice() + "원/ 재고 : " + fruits[i].getStock() + "개");
    }
  }

  // 수익현황 추가 메서드
  private void addIncomeList(Income income) {
    Income[] copyOf = Arrays.copyOf(incomeList, incomeList.length + 1); // 복사 + 사이즈 1개 추가
    copyOf[copyOf.length - 1] = income; // 마지막에 수익내역 추가
    incomeList = copyOf;                // 추가된 내역 재 저장
  }

  // 내역확인
  private String printIncome(Income income) {
    Fruit fruit = income.getFruit();
    IncomeType incomeType = income.getType();
    return fruit.getName() + " " + incomeType.getOp() + fruit.getPrice() + " * " + income.getCount() + " = " +
        incomeType.getOp() + (income.getCount() * fruit.getPrice()) + "원";
  }

  // 총금액 구하기(수익현황)
  private int calcTotalPrice(Income income) {
    IncomeType type = income.getType();
    Fruit fruit = income.getFruit();
    int total = fruit.getPrice() * income.getCount();
    return type == IncomeType.BUYING ? -total : total;  // 매입은 - 로 총 가격, 매출은 +로 총 가격 반환
  }

}
