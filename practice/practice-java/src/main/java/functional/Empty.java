package functional;

import java.util.function.Predicate;

public class Empty<T> implements MyList<T> {
  @Override
  public T head() {
    new UnsupportedOperationException();
    return null;
  }

  @Override
  public MyList<T> tail() {
    new UnsupportedOperationException();
    return null;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public MyList<T> filter(Predicate<T> predicate) {
    return null;
  }
}
