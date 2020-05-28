package chap19_functional;

import java.util.function.Predicate;

public interface MyList<T> {
  T head();

  MyList<T> tail();

  default boolean isEmpty() {
    return true;
  }

  default MyList<T> filter(Predicate<T> predicate) {
    return isEmpty() ?
            this :
            predicate.test(head()) ?
                    new LazyList<>(head(), () -> tail().filter(predicate)) :
                    tail().filter(predicate)
            ;
  }
}
