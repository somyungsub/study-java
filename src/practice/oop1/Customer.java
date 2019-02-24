package practice.oop1;


import java.util.Scanner;
import java.util.stream.Stream;

/*
    주문하는 고객
 */
public class Customer {
    private OrderInfo orderInfo = new OrderInfo();

    // 주문하기
    public void orderCoffee() {

        // 메뉴정보출력
        printMenuInfo();

        // 메뉴주문하기
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("메뉴이름 수량 형식으로 입력 \n");
            System.out.println("주문종료 (0 0)");

            String coffeeName = scanner.next();
            int count = scanner.nextInt();

            if ("0".equals(coffeeName) && count == 0) {
                scanner.close();
                return;
            }

            addOrderInfo(coffeeName, count);
        }

    }

    // 메뉴정보 출력하기
    private void printMenuInfo() {
        System.out.println(" ======= 커피 종류 및 가격 =======");
        Stream.of(Coffee.values()).forEach(coffee -> {
            System.out.println(coffee.getCoffeeName() + " : " + coffee.getPrice());
        });
    }

    // 주문정보 추가하기
    private void addOrderInfo(String coffeeName, int count) {
        // 메뉴 추가하기
        for (int i = 0; i < count; i++) {
            orderInfo.add(selectCoffee(coffeeName));
        }
    }

    // 메뉴선택하기
    private Coffee selectCoffee(String coffeeName) {
        return Stream.of(Coffee.values())
                .filter(coffee ->coffee.getCoffeeName().equals(coffeeName))
                .findFirst().get();
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }
}
