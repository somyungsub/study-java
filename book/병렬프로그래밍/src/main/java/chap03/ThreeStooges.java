package chap03;

import java.util.HashSet;
import java.util.Set;

public final class ThreeStooges {

  /*
    final
      - 변경 x
      - 외부 참조 없게 구현된 메서드들
   */
  private final Set<String> stooges = new HashSet<>();

  public ThreeStooges() {
    stooges.add("Moe");
    stooges.add("Larry");
    stooges.add("Curly");
  }

  public boolean isStooge(String name) {
    return stooges.contains(name);
  }


}
