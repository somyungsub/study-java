package chap04;

public class MutablePoint {

  public int x;
  public int y;

  public MutablePoint() {
    x = 0;
    y = 0;
  }

  public MutablePoint(MutablePoint mutablePoint) {
    this.x = mutablePoint.x;
    this.y = mutablePoint.y;
  }


}
