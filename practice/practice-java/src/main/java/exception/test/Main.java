package exception.test;

public class Main {
  public static void main(String[] args) {
    System.out.println("Test");

    try {
      Service service = new Service();
      service.test();
      System.out.println("에러 시 실행 ?????????");
    } catch (Exception e) {
      System.out.println("outer log");
    } finally {
      System.out.println("finaly");
    }
  }
}
