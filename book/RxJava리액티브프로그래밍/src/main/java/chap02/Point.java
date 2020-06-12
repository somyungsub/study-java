package chap02;

import java.util.concurrent.atomic.AtomicInteger;

public class Point {

  private final AtomicInteger x = new AtomicInteger(0);
  private final AtomicInteger y = new AtomicInteger(0);

  void rightUp() {
    x.incrementAndGet();
    y.incrementAndGet();
  }

  public AtomicInteger getX() {
    return x;
  }

  public AtomicInteger getY() {
    return y;
  }
}
