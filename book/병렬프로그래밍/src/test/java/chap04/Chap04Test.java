package chap04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

class Chap04Test {

  @Test
  @DisplayName("자바모니터 패턴")
  public void monitor() throws Exception{
    PrivateLock privateLock = new PrivateLock();
    CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
      for (int i = 0; i < 10_000; i++) {
        privateLock.someMethod();
      }
    });
    CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
      for (int i = 0; i < 10_000; i++) {
        privateLock.someMethod();
      }
    });

    System.out.println("============");
    System.out.println(cf1.thenCompose(aVoid -> cf2).get());
    System.out.println("============");

    System.out.println("privateLock.getCount() = " + privateLock.getCount());
  }

  @Test
  @DisplayName("블로킹큐")
  public void blocking_queue() throws InterruptedException {
    BlockingQueue bq = new ArrayBlockingQueue(10);
    System.out.println("bq.remainingCapacity() = " + bq.remainingCapacity());
    System.out.println("bq.size() = " + bq.size());

    for (int i = 0; i <11 ; i++) {
      bq.put(i);
      if (bq.size() == 10) {
        System.out.println("bq.size() = " + bq.size());
        System.out.println("bq.take()1 = " + bq.take());
        System.out.println("bq.take()2 = " + bq.take());
        System.out.println("bq.take()3 = " + bq.take());
        System.out.println("bq.take()4 = " + bq.take());
//        bq.forEach(o -> {
//          try {
//            System.out.println("o = " + bq.take());
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        });
      }
    }
    System.out.println("===============");
    System.out.println("bq.size() = " + bq.size());

  }

}