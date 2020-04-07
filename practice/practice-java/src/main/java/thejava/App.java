package thejava;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
  public static void main(String[] args) throws ClassNotFoundException {

    // Type 클래스가 만들어짐
    final Class<Book> bookClass = Book.class;
    final Field[] fields = bookClass.getFields();
    Arrays.stream(fields).forEach(System.out::println);

    final Field[] declaredFields = bookClass.getDeclaredFields();
    Arrays.stream(declaredFields).forEach(System.out::println);

    Book book1 = new Book();
    Arrays.stream(declaredFields).forEach(f->{
      f.setAccessible(true);  // 클래스 접근 가능하게 설정
      try {
        System.out.println("f.get(book1) = " + f.get(book1));
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    });

    Arrays.stream(bookClass.getMethods()).forEach(System.out::println);

    Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);

    System.out.println("MyBooK.class.getSuperclass() = " + MyBooK.class.getSuperclass());

    Arrays.stream(MyBooK.class.getInterfaces()).forEach(System.out::println);

    Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
      final int modifiers = f.getModifiers();
      System.out.println(f);
      System.out.println("Modifier.isPrivate(modifiers) = " + Modifier.isPrivate(modifiers));
      System.out.println("Modifier.isPublic(modifiers) = " + Modifier.isPublic(modifiers));
    });

    Arrays.stream(Book.class.getMethods()).forEach(m -> {
      final int modifiers = m.getModifiers();
      m.getReturnType();
      m.getParameters();
    });

    // Class 인스턴스로 접근
    Book book = new Book();
    final Class<? extends Book> aClass = book.getClass();

    // 풀패키지명으로
    final Class<?> aClass1 = Class.forName("thejava.Book");

  }
}
