package chap02;

import java.util.concurrent.atomic.AtomicLong;

public class CountingFactorizer {
  private final AtomicLong count = new AtomicLong(0);
//  private long count = 0;

  public long getCount() {
    return count.get();
//    return count;
  }

  public void increment() {
    count.incrementAndGet();
//    count++;
  }
}
