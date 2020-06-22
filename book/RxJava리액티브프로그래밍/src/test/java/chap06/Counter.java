package chap06;

public class Counter {
  private volatile int count;

  void increment() {
    count++;
  }

  public int get() {
    return count;
  }
}
