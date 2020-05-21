package chap02;

import java.util.Arrays;
import java.util.stream.Stream;

public class PasswordStrengthMeter {
  public PasswordStrength meter(String password) {
    if (!containsRegex(password, "[0-9]")) {
      return PasswordStrength.WEAK;
    }
    if (!isLengthFill(password)) {
      return PasswordStrength.NORMAL;
    }
    return PasswordStrength.STRONG;
  }

  private boolean isLengthFill(String password){
    return password.length() >= 8;
  }

  private boolean containsRegex(String password, String regex) {

    long count = Arrays.stream(password.split("")) // Stream<String[]>
            .filter(s -> s.matches(regex))
            .count();
    return count > 0;
  }

}
