package chap03_코드단위는간단하게;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chap03_코드단위는간단하게.Nationality.*;

public class ConditionChain {

  private static Map<Nationality, List<Color>> FLAGS = new HashMap<>();
  private static Map<Nationality, Flag> FLAGS2 = new HashMap<>();

  static {
    FLAGS.put(DUTCH, Arrays.asList(Color.RED, Color.WHITE));
    FLAGS.put(GERMAN, Arrays.asList(Color.RED, Color.WHITE, Color.BLACK));
    FLAGS.put(FRENCH, Arrays.asList(Color.RED, Color.BLUE, Color.YELLOW));
    FLAGS.put(KOREA, Arrays.asList(Color.BLUE, Color.WHITE, Color.RED));
  }

  static {
    FLAGS2.put(DUTCH, new DutchFlag());
    FLAGS2.put(GERMAN, new GermanFlag());
    FLAGS2.put(FRENCH, new FrenchFlag());
    FLAGS2.put(KOREA, new KoreaFlag());
  }

  public List<Color> getFlagColors(Nationality nationality) {
    List<Color> colors = FLAGS.get(nationality);
    return colors != null ? colors : Arrays.asList(Color.GRAY);
  }

  public List<Color> getFlagColors2(Nationality nationality) {
    Flag flag = FLAGS2.get(nationality);
    flag = flag != null ? flag : new DefaultFlag();
    return flag.getColors();
  }


}
