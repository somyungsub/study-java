package thejava.proxy;

public class DefaultBookService implements BookService{

  @Override
  public void rent() {
    System.out.println("rent : book");
  }
}
