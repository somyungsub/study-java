package chap03;

public class Counter {
  private volatile int count;

  void increment() {
    count++;
  }

  int get() {
    return count;
  }

}
