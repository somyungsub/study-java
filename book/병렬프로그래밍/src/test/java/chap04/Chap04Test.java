package chap04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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


}