package thejava;


@MyAnnotation("sso")
public class Book {
  private String a = "a";
  private static String B = "BOOK";
  private static final String C = "BOOK";
  public String d = "d";

  @MyAnnotation("eeee")
  protected String e = "e";

  @MyAnnotation("aasdada")
  public Book() {
  }

  @Override
  public String toString() {
    return "Book{" +
            "a='" + a + '\'' +
            ", d='" + d + '\'' +
            ", e='" + e + '\'' +
            '}';
  }


  @MyAnnotation("fff")
  private void f() {
    System.out.println("F");
  }

  public void g() {
    System.out.println("g");
  }

  public int h() {
    return 100;
  }

}
