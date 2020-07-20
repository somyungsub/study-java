package chap06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {

  private static final int NTHREADS = 100;
  private static final Executor executor = Executors.newFixedThreadPool(NTHREADS);

  public static void main(String[] args) throws IOException {
    ServerSocket socket = new ServerSocket(80);

    while (true) {
      final Socket connection = socket.accept();
      Runnable task = () -> handleRequest(connection);
      executor.execute(task);
    }
  }

  private static void handleRequest(Socket connection) {
    System.out.println("connection = " + connection);
  }
}
