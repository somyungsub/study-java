package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Volataile {
  volatile int count;
  AtomicInteger atomicInteger = new AtomicInteger(0);

  public void increment() {
    count++;
  }

  public void incrementAndGet() {
    atomicInteger.incrementAndGet();
  }

  public int getAtomic() {
    return atomicInteger.get();
  }

  public int getCount() {
    return count;
  }
}
