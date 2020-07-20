package chap06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorCompletionServiceClass {

  private ExecutorService executor = Executors.newFixedThreadPool(50);
  private ExecutorCompletionService<Double> service;

  public ExecutorCompletionServiceClass() {
    service = new ExecutorCompletionService<>(executor);
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(80);

    while (!executor.isShutdown()) {
      final Socket connection = serverSocket.accept();
      long start = System.currentTimeMillis();
      for (int i = 0; i < 50; i++) {
        service.submit(() -> handleRequest(connection));
      }

      System.out.println("========");
      try {
        Double sum = 0.0;
        for (int i = 0; i < 50; i++) {
          Future<Double> take = service.take();
          Double data = take.get();
          System.out.println("data = " + data);
          sum += data;
        }

        System.out.println("total = " + sum);

        System.out.println("(System.currentTimeMillis() - start) = " + (System.currentTimeMillis() - start) + " ms");
      } catch (InterruptedException e) {
        System.out.println("인터럽트 에" + e);
      } catch (ExecutionException e) {
        System.out.println("익스큐 에" + e);
      }

      connection.close();
    }
  }

  private Double handleRequest(Socket connection) {
    System.out.println("connection = " + connection);
    Double sum = 0.0;
    for (int i = 0; i < 1_000; i++) {
       sum += Math.random();
    }
    return sum;
  }

  public static void main(String[] args) throws IOException {
    new ExecutorCompletionServiceClass().start();
  }

}
