package practice.oop2;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.*;

public class ReservationBiz implements IReservationBiz {

    private int money;          // 잔액
    private List<Art> artList;  // Art타입 클래스 저장

    public ReservationBiz(int money) {
        this.money = money;
        artList = new ArrayList<>();
    }

    @Override
    public void addReservation(Art art) {
        System.out.println("현재 잔액 : " + money + "원");
        System.out.println(art.toString());

        if (!isReservation(art)) {
            System.out.println("잔액이 부족하여 " + art.getClass().getSimpleName() + " 예매 불가능합니다.");
            return;
        }

        artList.add(art);               // 추가
        money = calculateMoney(art);    // 현재 잔액 갱신
    }

    // 예매 가능 여부 확인
    private boolean isReservation(Art art){
        return calculateMoney(art) >= 0;
    }

    // 잔액 계산
    private int calculateMoney(Art art) {
        return money - (art.getPrice() * art.getReservationNum());
    }

    @Override
    public void printReservation() {
        System.out.println("===== 예매 목록 =====");
        artList.stream().collect(groupingBy(art -> art.getClass().getSimpleName(), summingInt(art -> art.getReservationNum())))
               .forEach((name, reservation) -> {
                   System.out.println(name + " : " + reservation + "개");
               });
        System.out.println("===================");
        System.out.println("전체 예매 금액 : " + getTotalMoney() + "원");
        System.out.println("남은 금액 = " + money + "원");
    }

    private int getTotalMoney() {
        return artList.stream().collect(summingInt(art -> art.getPrice() * art.getReservationNum()));
    }
}

