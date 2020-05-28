package chap19_functional;

public class Empty<T> implements MyList<T> {

  @Override
  public T head() {
    throw new UnsupportedOperationException();
  }

  @Override
  public MyList<T> tail() {
    throw new UnsupportedOperationException();
  }
}
