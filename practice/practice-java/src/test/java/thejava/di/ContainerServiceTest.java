package thejava.di;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ContainerServiceTest {

  @Test
  public void getObject_BookRepository() {
    final BookRepository bookRepository = ContainerService.getObject(BookRepository.class);
    assertNotNull(bookRepository);
  }

  @Test
  public void getObject_BookService() {
    final BookService bookService = ContainerService.getObject(BookService.class);
    assertNotNull(bookService);
    assertNotNull(bookService.bookRepository);
  }

}