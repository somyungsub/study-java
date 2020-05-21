package chap02;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class PasswordStrengthMeter {
  public PasswordStrength meter(String password) {

    if (StringUtils.isEmpty(password)) {
      return PasswordStrength.INVALID;
    }

    if (!isLengthFill(password) || !containsRegex(password, "[A-Z]")) {
      return PasswordStrength.NORMAL;
    }

    if (!containsRegex(password, "[0-9]")) {
      return PasswordStrength.WEAK;
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
