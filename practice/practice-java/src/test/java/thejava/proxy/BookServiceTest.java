package thejava.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BookServiceTest {

  //  BookService bookService = new BookServiceProxy(new DefaultBookService());

  // 다이나믹 프록시
  BookService bookService = (BookService) Proxy.newProxyInstance(
      BookService.class.getClassLoader(),
      new Class[]{BookService.class},
      new InvocationHandler() {

        BookService bookService = new DefaultBookService(); // real 서브젝트
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          // 부가적인 기능 구현
          System.out.println("bbbb2");
          final Object invoke = method.invoke(bookService, args);
          System.out.println("aaaa2");
          return invoke;
        }
      }
  );

  @Test
  public void test() {
    bookService.rent();
    bookService.returnBook(new Book());
  }
}