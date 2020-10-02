package chapter_06;

import java.util.Set;
import java.util.stream.Collectors;

public class StreamPractice {

  public String usersTuple(final Set<String> following) {
    return following.stream()
      .map(id -> "'" + id + "'")
      .collect(Collectors.joining(",", "(", ")"));
  }
}
