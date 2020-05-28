package chap19_functional;

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
    return tail.get();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  public static LazyList<Integer> from(int n) {
    return new LazyList<Integer>(n, () -> from(n + 1));
  }

  public static MyList<Integer> primes(MyList<Integer> numbers) {
    return new LazyList<>(
            numbers.head(),
            () -> primes(
                    numbers.tail()
                            .filter(n -> n % numbers.head() != 0)
            )
    );
  }

  public static <T> void printAll(MyList<T> list) {
    if (list.isEmpty()) {
      return;
    }

    System.out.println(list.head());
    printAll(list.tail());
  }

}
