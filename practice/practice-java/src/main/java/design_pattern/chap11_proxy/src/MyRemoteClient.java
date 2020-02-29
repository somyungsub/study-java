package design_pattern.chap11_proxy.src;

import java.rmi.Naming;

public class MyRemoteClient {
  public static void main(String[] args) {
    new MyRemoteClient().go();
  }

  private void go() {
    try {
      MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/remoteHello");
      String s = service.sayHello();
      System.out.println("s = " + s);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
