package chap04;

public class PrivateLock {
  private final Object lock = new Object(); // Lock 객체로 쓰기

  int count;

  void someMethod() {
    synchronized (lock) {
      count++;
    }
  }

  public synchronized int getCount() {
    return count;
  }
}
