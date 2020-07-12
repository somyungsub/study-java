package net;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.*;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class NetTest {

  @Test
  @DisplayName("InetAddress 사용")
  public void inetAddress() throws UnknownHostException {
    InetAddress inetAddress = InetAddress.getByName("localhost");
    System.out.println("inetAddress.getHostAddress() = " + inetAddress.getHostAddress());
    System.out.println("inetAddress.getHostName() = " + inetAddress.getHostName());
    System.out.println("inetAddress.getCanonicalHostName() = " + inetAddress.getCanonicalHostName());
    System.out.println("inetAddress.getAddress() = " + inetAddress.getAddress());

    InetAddress[] allByName = InetAddress.getAllByName("localhost");
    for (InetAddress address : allByName) {
      System.out.println("address = " + address);
    }

    InetAddress localHost = InetAddress.getLocalHost();
    System.out.println("localHost = " + localHost);
  }

  @Test
  @DisplayName("getByAddress")
  public void getByAddress() throws UnknownHostException {
    byte[] address = {127, 0, 0, 1};
    InetAddress localhost2 = InetAddress.getByAddress("localhost2", address);
    System.out.println("localhost2 = " + localhost2);

    InetAddress localhost = InetAddress.getByName("localhost");
    System.out.println("localhost = " + localhost);

  }


  @Test
  @DisplayName("InetAddress 사용2")
  public void inetAddress2() throws UnknownHostException {
//    InetAddress inetAddress = InetAddress.getByName("localhost2");
    InetAddress inetAddress = InetAddress.getByName("www.oreilly.com");
    System.out.println("inetAddress.getHostAddress() = " + inetAddress.getHostAddress());
    System.out.println("inetAddress.getHostName() = " + inetAddress.getHostName());
    System.out.println("inetAddress.getCanonicalHostName() = " + inetAddress.getCanonicalHostName());
    System.out.println("inetAddress.getAddress() = " + inetAddress.getAddress());

    InetAddress[] allByName = InetAddress.getAllByName("www.oreilly.com");
    for (InetAddress address : allByName) {
      System.out.println("address = " + address);
    }
  }

  @Test
  @DisplayName("InetAddress 사용 - ip")
  public void inetAddress_ip() throws UnknownHostException {
    InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
    System.out.println("inetAddress.getHostAddress() = " + inetAddress.getHostAddress());
    System.out.println("inetAddress.getHostName() = " + inetAddress.getHostName());
    System.out.println("inetAddress.getCanonicalHostName() = " + inetAddress.getCanonicalHostName());
    System.out.println("inetAddress.getAddress() = " + inetAddress.getAddress());
  }

  @Test
  @DisplayName("network interface")
  public void net_interface() throws SocketException, UnknownHostException {
    NetworkInterface lo = NetworkInterface.getByName("lo0");
    System.out.println("lo = " + lo);
    System.out.println("lo.getName() = " + lo.getName());
    System.out.println("lo.isVirtual() = " + lo.isVirtual());

    InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
    System.out.println("networkInterface = " + networkInterface);

    System.out.println(System.getProperty("http.proxyHost"));
    System.out.println(System.getProperty("http.proxyPort"));
    System.out.println(System.getProperty("http.nonProxyHosts"));
  }

  @DisplayName("URL")
  @ParameterizedTest(name = "{index} : {displayName}")
  @ValueSource(strings = {
      "http://www.adc.org",
      "https://www.amazon.com/exec/obidos/order2/",
      "ftp://a/b/c",
      "telnet://abc.asd/",
      "file:///etc/passwd"
  })
  public void url(String url) {
    // 위 프로콜외 다양 자바 네트워크 프로그래밍 -> p.157 참조
    try {
      URL u = new URL(url);
      System.out.println(u.getProtocol() + " is supported");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }



}