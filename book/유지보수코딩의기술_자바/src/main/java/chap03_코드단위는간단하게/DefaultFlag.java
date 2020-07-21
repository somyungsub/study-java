package chap03_코드단위는간단하게;

import java.util.Arrays;
import java.util.List;

public class DefaultFlag implements Flag {
  @Override
  public List<Color> getColors() {
    return Arrays.asList(Color.GRAY);
  }
}
