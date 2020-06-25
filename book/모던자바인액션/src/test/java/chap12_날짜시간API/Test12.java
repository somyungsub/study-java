package chap12_날짜시간API;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class Test12 {
  @Test
  @DisplayName("duration")
  public void duration() {
    LocalDateTime now = LocalDateTime.now();
    Duration between = Duration.between(now, now.plusSeconds(600));
    System.out.println("between = " + between);
    System.out.println("between.toSeconds() = " + between.toSeconds());
    if (between.toSeconds() == 600) {
      System.out.println("!!!");
    }
  }
}
