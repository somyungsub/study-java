package concurrent;

public class Volataile {
  volatile int count;

  public void increment() {
    count++;
  }

  public int getCount() {
    return count;
  }
}
