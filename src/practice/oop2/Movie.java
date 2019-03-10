package practice.oop2;

public class Movie extends Art{
    public static final int DRAMA_PRICE = 50;

    public Movie(int reservationNum) {
        super(DRAMA_PRICE, reservationNum);
    }

    @Override
    public String toString() {
        return "영화" + super.toString();
    }
}
