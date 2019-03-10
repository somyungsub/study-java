package practice.oop2;

public class ReservationTest {
    public static void main(String[] args) {
        ReservationBiz biz = new ReservationBiz(1000);
        biz.addReservation(new Movie(2));
        biz.addReservation(new Musical(1));
        biz.addReservation(new Movie(1));
        biz.addReservation(new Drama(2));
        biz.addReservation(new Musical(2));
//        biz.addReservation(new Musical(1));

        biz.printReservation();

    }
}
