package chap02;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class PasswordStrengthMeter {
  public PasswordStrength meter(String password) {

    if (StringUtils.isEmpty(password)) {
      return PasswordStrength.INVALID;
    }

    boolean containsNum = containsRegex(password, "[0-9]");
    boolean containsUpper = containsRegex(password, "[A-Z]");
    boolean isLengthFill = isLengthFill(password);

    if (isLengthFill && !containsNum && ! containsUpper) {
      return PasswordStrength.WEAK;
    }

    if (!isLengthFill && containsNum && !containsUpper) {
      return PasswordStrength.WEAK;
    }

    if (!isLengthFill && !containsNum && containsUpper) {
      return PasswordStrength.WEAK;
    }

    if (!isLengthFill || !containsUpper || !containsNum) {
      return PasswordStrength.NORMAL;
    }

    return PasswordStrength.STRONG;
  }

  private boolean isLengthFill(String password){
    return password.length() >= 8;
  }

  private boolean containsRegex(String password, String regex) {

    long count = Arrays.stream(password.split(""))
            .filter(s -> s.matches(regex))
            .count();

    return count > 0;
  }

}
