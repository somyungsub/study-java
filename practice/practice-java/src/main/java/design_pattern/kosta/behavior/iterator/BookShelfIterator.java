package design_pattern.kosta.behavior.iterator;

public class BookShelfIterator implements MyIterator{

  private BookShelf bookShelf;
  private int index;

  public BookShelfIterator(BookShelf bookShelf) {
    this.bookShelf = bookShelf;
    index = 0;
  }

  @Override
  public boolean hasNext() {
    if (index < bookShelf.getLength()) {
      return true;
    }
    return false;
  }

  @Override
  public Object next() {
    Book book = bookShelf.getBookAt(index);
    index++;
    return book;
  }
}
