package practice.oop2;

public class Drama extends Art{
    public static final int MOVIE_PRICE = 100;

    public Drama(int reservationNum) {
        super(MOVIE_PRICE, reservationNum);
    }

    @Override
    public String toString() {
        return "연극" + super.toString();
    }
}
