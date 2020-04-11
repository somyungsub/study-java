package thejava.cglib;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class BookCglibServiceTest {

  /*
   class 기반의 프록시가 가능 -> CGLIB 활용
   */
  @Test
  public void cglib() {
    MethodInterceptor handler = new MethodInterceptor() {
      BookService bookService = new BookService();

      @Override
      public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("aaaa");
        final Object invoke = method.invoke(bookService, args);
        System.out.println("bbbb");

        return invoke;
      }
    };

    BookService bookService
        = (BookService) Enhancer.create(BookService.class, handler);

    Book book = new Book();
    book.setTitle("spring");
    bookService.rent(book);
    bookService.returnBook(book);
  }

  /*
    ByteBuddy 활용한 프록시
    - final 클래스의 경우 안됨 (상속불가)
    - private 생성자만 있는 경우
   */
  @Test
  public void bytebuddy() throws Exception {
    final Class<? extends BookService> proxyClass
        = new ByteBuddy().subclass(BookService.class)
        .method(named("rent")) // 해당 메서드(rent 메서드)에 대해서 적용하겠다!
        .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
          BookService bookService = new BookService();
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("=========");
            final Object invoke = method.invoke(bookService, args);
            System.out.println("=========");
            return invoke;
          }
        }))
        .make().load(BookService.class.getClassLoader()).getLoaded();

    final BookService bookService = proxyClass.getConstructor(null).newInstance();

    Book book = new Book();
    book.setTitle("spring");
    bookService.rent(book);
    bookService.returnBook(book);
  }
}