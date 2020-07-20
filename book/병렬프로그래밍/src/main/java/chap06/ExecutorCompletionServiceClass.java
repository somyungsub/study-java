package chap06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.*;

public class ExecutorCompletionServiceClass {

  private ExecutorService executor = Executors.newFixedThreadPool(50);
  private ExecutorCompletionService service;

  public ExecutorCompletionServiceClass() {
    service = new ExecutorCompletionService(executor);
  }

  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(80);

    while (!executor.isShutdown()) {
      final Socket connection = serverSocket.accept();
      double result = 0.0;
      service.submit(() -> handleRequest(connection), result);
      System.out.println("result = " + result);

//      try {
//
//
//      } catch (InterruptedException e) {
//        System.out.println("인터럽트 에" + e);
//      } catch (ExecutionException e) {
//        System.out.println("익스큐 에" + e);
//      }
    }
  }

  private Object handleRequest(Socket connection) {
    System.out.println("connection = " + connection);
//    try {
//      Thread.sleep(1500);
//    } catch (InterruptedException e) {
//      System.out.println("test!! error " + e);
//    }

    double random = Math.random();
    System.out.println("random = " + random);
    return random;
  }

  public static void main(String[] args) throws IOException {
    new ExecutorCompletionServiceClass().start();
  }

}
