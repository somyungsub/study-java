package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class HolderTest {
  Holder holder = new Holder(1);
  @Test
  @DisplayName("안전 공개")
  public void safe_public() throws ExecutionException, InterruptedException {

    Runnable r1 = () -> {

      for (int i = 0; i < 100_000; i++) {
        holder.assertSanity();
      }
    };
    Runnable r2 = () -> {
      for (int i = 0; i < 100_000; i++) {
        holder.assertSanity();
      }
    };
    Runnable r3 = () -> {
      for (int i = 0; i < 10_000; i++) {
        holder.assertSanity();
      }
    };

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Future<?> submit1 = executorService.submit(r1);
    Future<?> submit2 = executorService.submit(r2);
    Future<?> submit3 = executorService.submit(r3);

    submit1.get();
    submit2.get();
    submit3.get();

    executorService.shutdown();


  }

  @Test
  @DisplayName("hashmap")
  public void test() throws ExecutionException, InterruptedException {
    MapImmutable mapImmutable = new MapImmutable();

    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> execute(mapImmutable));
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> execute(mapImmutable));

    System.out.println("===========");
    System.out.println(cf1.get());
    System.out.println(cf2.get());
    System.out.println("===========");
    System.out.println(mapImmutable.getMap().size());

  }

  private String execute(MapImmutable mapImmutable) {
    for (int i = 0; i < 10_000; i++) {
      mapImmutable.add(i);
    }
    return Thread.currentThread().getName();
  }

}