package net;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.stream.Stream;

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

  @Test
  @DisplayName("네트워크 인터페이스2")
  public void net_interface2() throws SocketException {
    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

    while (networkInterfaces.hasMoreElements()) {
      NetworkInterface networkInterface = networkInterfaces.nextElement();
      System.out.println("networkInterface = " + networkInterface);

      if (networkInterface.isLoopback()) {
        System.out.println("loopback = " + networkInterface);
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
          InetAddress inetAddress = inetAddresses.nextElement();
          System.out.println("inetAddress = " + inetAddress);
          System.out.println("inetAddress.getHostName() = " + inetAddress.getHostName());
          System.out.println("inetAddress.getCanonicalHostName() = " + inetAddress.getCanonicalHostName());
          System.out.println("inetAddress.getHostAddress() = " + inetAddress.getHostAddress());

        }
      }
    }
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

  @Test
  @DisplayName("URL2")
  public void url2() throws MalformedURLException {
    URL u = new URL("http", "localhost", "/abc"); // port 80(기본포트 사용)
    URL u2 = new URL("http", "localhost", 8080, "/abc");
    URL u3 = new URL("http", "localhost", 8080, "/abc/index.html");

    System.out.println("u = " + u);
    System.out.println("u2 = " + u2);
    System.out.println("u3 = " + u3);

    URL u4 = new URL(u3, "test.html");
    System.out.println("u4 = " + u4);
  }

  @Test
  @DisplayName("URLConnection - 헤더")
  public void header() throws Exception {
    URL u = new URL("http://localhost:8080");
    URLConnection urlConnection = u.openConnection();

    urlConnection.getHeaderFields()
        .forEach((key, values) -> {
          System.out.println("key : " + key);
          System.out.println("-> " + values);
        });
  }

  @Test
  @DisplayName("io")
  public void io() throws IOException {
    Files.newBufferedReader(Paths.get("/etc/hosts"))
        .lines()
        .filter(s -> !s.startsWith("#") && s.contains("127.0.0.1"))
        .flatMap(s -> Stream.of(s.split("\\s")))
        .filter(s -> !s.contains("127.0.0.1"))
        .forEach(System.out::println);
  }


}