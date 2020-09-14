package chapter_04;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class TextFile {
  private final Map<String, String> attributes = new HashMap<>();
  private final List<String> lines = new ArrayList<>();
  private final File file;


  public TextFile(File file) {
    this.file = file;
  }

  public void addLineSuffix(String prefix, String value) {
    attributes.put(prefix, value);
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }

  public int addLines(int start, Predicate<String> isEnd, String attributeName) {

    final StringBuilder accumulator = new StringBuilder();
    int lineNumber;

    for (lineNumber = start; lineNumber < lines.size(); lineNumber++) {
      final String line = lines.get(lineNumber);
      if (isEnd.test(line)) {
        break;
      }
      accumulator.append(line).append("\n");
    }

    attributes.put(attributeName, accumulator.toString().trim());
    return lineNumber;
  }
}
