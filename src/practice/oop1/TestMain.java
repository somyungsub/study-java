package practice.oop1;


/*
    OOP 연습문제1. 커피문제
    - 메뉴 : 커피 4종류
        - 아메리카노 : 1500원
        - 카푸치노 : 2000원
        - 카라멜마키아또 : 2500원
        - 에스프레소 : 2500원
    - 손님 : 커피주문
    - 바리스타 : 커피주문을 받고 커피를 제조함

    출력 내용
    - 커피 주문 내역
      -> 각 몇잔, 각가격, 총 금액 (영수증 내용)

 */
public class TestMain {
    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.orderCoffee();

        Barista barista = new Barista(customer);
        barista.createCoffe();
        barista.printReceipt();
    }
}
