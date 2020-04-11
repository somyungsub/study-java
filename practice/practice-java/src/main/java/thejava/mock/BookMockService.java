package thejava.mock;

public class BookMockService {

  BookRepository bookRepository;

  public BookMockService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public void rent(Book book) {
    final Book save = bookRepository.save(book);
    System.out.println("rent : " + save.getTitle());
  }

  public void returnBook(Book book) {
    bookRepository.save(book);
  }
}
