package design_pattern.chap07_adapter_facade.src;

public interface Iterator<E> {
    boolean hasNext();

    E next();

    void remove();
}
