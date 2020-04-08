package thejava;

import java.util.Arrays;

public class AnnotationMain {
  public static void main(String[] args) {
    Arrays.stream(MyBooK.class.getAnnotations()).forEach(System.out::println);
    Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
//    Arrays.stream(Book.class.getDeclaredAnnotations()).forEach(System.out::println);

    Arrays.stream(Book.class.getDeclaredFields())
            .forEach(f->{
//              Arrays.stream(f.getAnnotations()).forEach(System.out::println);
              Arrays.stream(f.getAnnotations()).forEach(a->{
                if (a instanceof MyAnnotation) {
                  MyAnnotation myAnnotation  = (MyAnnotation) a;
                  System.out.println(((MyAnnotation) a).value());
                  System.out.println(((MyAnnotation) a).number());
                }
              });
            });


  }
}
