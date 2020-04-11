package thejava.proxy;

public class BookServiceProxy implements BookService {
  DefaultBookService defaultBookService;

  public BookServiceProxy(DefaultBookService defaultBookService) {
    this.defaultBookService = defaultBookService;
  }

  @Override
  public void rent() {
    System.out.println("aaa");
    defaultBookService.rent();
    System.out.println("bbb");
  }
}
