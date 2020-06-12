package chap02;

public class SynchronizedPoint2 {
  private final Object lock = new Object();

  private int x;
  private int y;

  void rightUp() {
    synchronized (lock) {
      x++;
      y++;
    }
  }

  synchronized public int getX() {
    return x;
  }

  synchronized public int getY() {
    return y;
  }
}
