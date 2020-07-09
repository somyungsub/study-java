package chap11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ServerStatusTest {

  ServerStatus serverStatus = new ServerStatus(new HashSet<>(), new HashSet<>());

  @Test
  @DisplayName("락 객체 사용 (락분할)")
  public void rock() throws ExecutionException, InterruptedException {
    CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> test("No. 1"));
    CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> test("No. 2"));

    System.out.println(cf1.thenCompose(s -> cf2).get());

    System.out.println("serverStatus.getUsers().size() = " + serverStatus.getUsers().size());
  }

  private String test(String string) {
    for (int i = 0; i < 10_000; i++) {
      serverStatus.addUser(String.valueOf(i));
    }
    return string;
  }

}