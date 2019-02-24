package practice.oop1;

/*
    바리스타
    - 고객의 주문정보를 이용하여 커피 제조
 */
public class Barista {
    private Customer customer;

    public Barista(Customer customer) {
        this.customer = customer;
    }

    // 커피 제조하기
    public void createCoffe() {
        OrderInfo orderInfo = customer.getOrderInfo();
        orderInfo.getMenuInfo().forEach((coffee, count) -> {
            System.out.println(coffee + "를 " + count + "잔 만들었습니다");
        });
    }

    // 영수증 반환해주기
    public void printReceipt() {
        OrderInfo orderInfo = customer.getOrderInfo();
        System.out.println(" ======= 영수증 =======");
        orderInfo.getMenuInfo().forEach((coffee, count) -> {
            System.out.println(coffee + " : " + count + " | " + (coffee.getPrice() * count) + "원");
        });
        System.out.println("합계 : "+ orderInfo.getTotalPrice());
    }
}
