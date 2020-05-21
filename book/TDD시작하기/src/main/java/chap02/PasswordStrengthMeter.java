package chap02;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class PasswordStrengthMeter {

  public PasswordStrength meter(String password) {

    if (StringUtils.isEmpty(password)) {
      return PasswordStrength.INVALID;
    }

    int metCounts = getMetCounts(password);
    if (metCounts <= 1) {
      return PasswordStrength.WEAK;
    }
    if (metCounts == 2) {
      return PasswordStrength.NORMAL;
    }
    return PasswordStrength.STRONG;
  }

  private int getMetCounts(String password) {

    boolean containsNum = containsRegex(password, "[0-9]");
    boolean containsUpper = containsRegex(password, "[A-Z]");
    boolean isLengthFill = isLengthFill(password);

    int metCounts = 0;

    if (containsNum) metCounts++;
    if (containsUpper) metCounts++;
    if (isLengthFill) metCounts++;

    return metCounts;
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
