package chap03_코드단위는간단하게;

import java.util.Arrays;
import java.util.List;

public class KoreaFlag implements Flag{

  @Override
  public List<Color> getColors() {
    return Arrays.asList(Color.BLUE, Color.WHITE, Color.RED);
  }
}
