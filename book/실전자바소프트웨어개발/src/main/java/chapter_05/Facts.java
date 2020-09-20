package chapter_05;

import java.util.HashMap;
import java.util.Map;

public class Facts {

  private final Map<String, String> facts = new HashMap<>();

  public String getFact(final String name) {
    return facts.get(name);
  }

  public void addFact(final String name, final String value) {
    this.facts.put(name, value);
  }

}
