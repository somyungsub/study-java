package chap07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PrimeProducer extends Thread {

  private final BlockingQueue<BigInteger> queue;

  public PrimeProducer(BlockingQueue<BigInteger> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      BigInteger p = BigInteger.ONE;
      while (!Thread.currentThread().isInterrupted()) {
        queue.put(p = p.nextProbablePrime());
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public void cancel() {
    interrupt();
  }


}
