package chap06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class LifecycleWebServer {
  private final ExecutorService executorService = Executors.newFixedThreadPool(50);

  public void start() throws IOException {
    ServerSocket socket = new ServerSocket(80);

    while (!executorService.isShutdown()) {
      try {
        final Socket connection = socket.accept();
        executorService.execute(() -> handleRequest(connection));
      } catch (RejectedExecutionException e) {
        if (!executorService.isShutdown()) {
          System.out.println("task submission rejected : " + e);
        }
      }
    }
  }

  private void handleRequest(Socket connection) {
    Request req = readRequest(connection);
    if (isShutdownRequest(req)) {
      stop();
    } else {
      dispatchRequest(req);
    }
  }

  private void dispatchRequest(Request req) {
    System.out.println("req 처리");
    System.out.println("req = " + req);
  }

  private boolean isShutdownRequest(Request req) {
    return req == null;
  }

  private Request readRequest(Socket connection) {
    return new Request(connection);
  }

  public void stop() {
    executorService.shutdown();
  }

}
