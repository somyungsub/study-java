package functional;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
  final T head;
  final Supplier<MyList<T>> tail;

  public LazyList(T head, Supplier<MyList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }


  @Override
  public T head() {
    return head;
  }

  @Override
  public MyList<T> tail() {
    return tail.get();  // 게으른동작
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  public static LazyList<Integer> from(int n) {
    return new LazyList<Integer>(n, () -> from(n + 1));
  }

  public MyList<Integer> primes(MyList<Integer> numbers) {
    return new LazyList<>(
        numbers.head(),
        () -> primes(
            numbers.tail().filter(n -> n % numbers.head() != 0)
        )
    );
  }

  @Override
  public MyList<T> filter(Predicate<T> predicate) {
    return  isEmpty() ?
            this :
            predicate.test(head()) ?
                new LazyList<>(head(), () -> tail().filter(predicate))
                : tail().filter(predicate);

  }

}
