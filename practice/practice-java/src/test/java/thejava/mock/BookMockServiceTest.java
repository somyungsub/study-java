package thejava.mock;

import org.junit.Test;
import org.mockito.Mockito;

public class BookMockServiceTest {

  @Test
  public void mockito() throws Exception {

    // mock 생성
    BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);

    // when
    Book hibernateBook = new Book();
    hibernateBook.setTitle("Hibernate");
    Mockito.when(bookRepositoryMock.save(Mockito.any())).thenReturn(hibernateBook);

    BookMockService bookMockService = new BookMockService(bookRepositoryMock);

    Book book = new Book();
    book.setTitle("spring");

    bookMockService.rent(book);         // 기대값 spring 이 아닌 Hibernate 출력 -> proxy 개념 적용
    bookMockService.returnBook(book);
  }

}