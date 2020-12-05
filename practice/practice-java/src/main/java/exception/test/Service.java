package exception.test;

public class Service {

  public void test() {
    System.out.println("seervic");
//    try {
      if (true) {
        throw new RuntimeException("error service");
      }
//    }
//    catch (Exception e) {
//      System.out.println(e);
//    }
    System.out.println("!!!!");
  }
}
