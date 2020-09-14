package chapter_04;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static chapter_04.Attributes.*;

public class ReportImporter implements Importer {
  private static final String NAME_PREFIX = "Patient: ";

  @Override
  public Document importFile(File file) throws IOException {
    final TextFile textFile = new TextFile(file);

    textFile.addLineSuffix(NAME_PREFIX, PATIENT);

    final int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
    textFile.addLines(2, line -> line.startsWith("regards,"), BODY);

    final Map<String, String> attributes = textFile.getAttributes();
    attributes.put(TYPE, "LETTER");

    return new Document(attributes);
  }
}
