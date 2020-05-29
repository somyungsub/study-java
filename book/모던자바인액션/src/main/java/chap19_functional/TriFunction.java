package chap19_functional;

@FunctionalInterface
public interface TriFunction<S,T,U, R> {
  R apply(S s, T t, U u);
}
