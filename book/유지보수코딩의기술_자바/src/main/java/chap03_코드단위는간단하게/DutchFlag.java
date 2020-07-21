package chap03_코드단위는간단하게;

import java.util.Arrays;
import java.util.List;

public class DutchFlag implements Flag {

  @Override
  public List<Color> getColors() {
    return Arrays.asList(Color.RED, Color.WHITE);
  }
}
