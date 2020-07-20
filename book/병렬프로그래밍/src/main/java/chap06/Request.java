package chap06;

import java.net.Socket;

public class Request {

  private final Socket connection;

  public Request(Socket connection) {
    this.connection = connection;
  }

}
