package chap04;

public class SafePoint {
  private int x;
  private int y;

  private SafePoint(int[] ints) {
    this(ints[0], ints[1]);
  }

  public SafePoint(SafePoint point) {
    this(point.get());
  }

  public SafePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }


  public synchronized int[] get() {
    return new int[]{x, y};
  }

  public synchronized void set(int x, int y) {
    this.x = x;
    this.y = y;
  }

}
