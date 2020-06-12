package chap02;

public class SynchronizedPoint {
  private final Object lock = new Object();

  private int x;
  private int y;

  void rightUp() {
    synchronized (lock) {
      x++;
      y++;
    }
  }

  public int getX() {
    synchronized (lock) {
      return x;
    }
  }

  public int getY() {
    synchronized (lock) {
      return y;
    }
  }
}
