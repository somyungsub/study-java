package chap06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWebServer {

  // 순차적 처리 웹서버
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(80);
    while (true) {
      Socket connection = serverSocket.accept();
      handleRequest(connection);
    }
  }

  private static void handleRequest(Socket connection) {
    //
    System.out.println("connection = " + connection);
  }
}
