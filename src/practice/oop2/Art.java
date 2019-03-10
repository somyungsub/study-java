package practice.oop2;

public class Art {
    private int price;
    private int reservationNum;

    public Art(int price, int reservationNum) {
        this.price = price;
        this.reservationNum = reservationNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(int reservationNum) {
        this.reservationNum = reservationNum;
    }

    @Override
    public String toString() {
        return "(가격 : " + price + "원) 예매를 " + reservationNum + " 장 합니다";
    }
}
