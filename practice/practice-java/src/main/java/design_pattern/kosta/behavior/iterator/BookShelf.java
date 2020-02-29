package design_pattern.kosta.behavior.iterator;

public class BookShelf implements MyAggregate {
  private Book[] books;
  private int last = 0;


  public BookShelf(int size) {
    this.books = new Book[size];
  }

  public Book getBookAt(int index) {
    return this.books[index];
  }

  public void addBook(Book book) {
    books[last] = book;
    last++;
  }

  public int getLength() {
    return books.length;
  }

  @Override
  public MyIterator iterator() {
    return new BookShelfIterator(this);
  }

}
