package chapter_06;

import java.util.Optional;

public interface AbstractRepository<T> {

  void add(T value);

  Optional<T> get(String id);

  void update(T value);

  void delete(T value);

}
