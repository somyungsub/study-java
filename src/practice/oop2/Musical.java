package practice.oop2;


public class Musical extends Art {
    public static final int MUSICAL_PRICE = 200;

    public Musical(int reservationNum) {
        super(MUSICAL_PRICE, reservationNum);
    }

    @Override
    public String toString() {
        return "뮤지컬" + super.toString();
    }
}
