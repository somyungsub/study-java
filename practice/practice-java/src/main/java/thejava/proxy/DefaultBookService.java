package thejava.proxy;

public class DefaultBookService implements BookService{

  @Override
  public void rent() {

    System.out.println("aaaa");
    System.out.println("rent : book");
    System.out.println("bbbb");
  }

  @Override
  public void returnBook(Book book) {
    System.out.println(book);
  }
}
