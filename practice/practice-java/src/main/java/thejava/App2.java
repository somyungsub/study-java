package thejava;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class App2 {
  public static void main(String[] args) throws Exception{
    final Class<?> bookClass = Class.forName("thejava.Book2");
    final Constructor<?> constructor = bookClass.getConstructor(null);
    final Book2 book = (Book2) constructor.newInstance();

    final Constructor<?> constructor2 = bookClass.getConstructor(String.class);
    final Book2 book2 = (Book2) constructor2.newInstance("myBook");
    System.out.println(book);
    System.out.println(book2);

    final Field a = Book2.class.getDeclaredField("A");
    System.out.println(a.get(null));
    a.set(null, "AAAAAA");
    System.out.println(a.get(null));

    final Field b = Book2.class.getDeclaredField("B");
    b.setAccessible(true);
    System.out.println(b.get(book2));
    b.set(book2, "AAAAAA");
    System.out.println(b.get(book2));


    final Method c = Book2.class.getDeclaredMethod("c");
    c.setAccessible(true);
    c.invoke(book);

    final Method d = Book2.class.getDeclaredMethod("sum", int.class, int.class);
    System.out.println(d.invoke(book, 1, 2));
  }

}
