package chap03_코드단위는간단하게;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chap03_코드단위는간단하게.Nationality.*;

public class ConditionChain {

  private static Map<Nationality, List<Color>> FLAGS = new HashMap<>();

  static {
    FLAGS.put(DUTCH, Arrays.asList(Color.RED, Color.WHITE));
    FLAGS.put(GERMAN, Arrays.asList(Color.RED, Color.WHITE, Color.BLACK));
    FLAGS.put(FRENCH, Arrays.asList(Color.RED, Color.BLUE, Color.YELLOW));
    FLAGS.put(KOREA, Arrays.asList(Color.BLUE, Color.WHITE, Color.RED));
  }

  public List<Color> getFlagColors(Nationality nationality) {
    List<Color> colors = FLAGS.get(nationality);
    return colors != null ? colors : Arrays.asList(Color.GRAY);
  }

}
