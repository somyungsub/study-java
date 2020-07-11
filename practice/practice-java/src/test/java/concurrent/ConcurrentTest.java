package concurrent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentTest {
  
  @Test
  @DisplayName("불변테스트")
  public void immutable(){
    Immutable immutable = new Immutable(1, "s");

    CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
      int a = immutable.getA();
      System.out.println("a++ = " + a++);
      String s = immutable.getS();
      System.out.println("s+\"a\" = " + s+"a");
    });
    
    CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
      int a = immutable.getA();
      System.out.println("a++ = " + a++);
      String s = immutable.getS();
      System.out.println("s+\"b\" = " + s+"b");
    });

    cf1.thenCompose(aVoid -> cf2);

    System.out.println("immutable.getA() = " + immutable.getA());
    System.out.println("immutable = " + immutable.getS());

  }

}