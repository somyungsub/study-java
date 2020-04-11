package thejava.proxy;

import org.junit.Test;

public class BookServiceTest {

  BookService bookService = new BookServiceProxy(new DefaultBookService());

  @Test
  public void test() {
    bookService.rent();
  }

}