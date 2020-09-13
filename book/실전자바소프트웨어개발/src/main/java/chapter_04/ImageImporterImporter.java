package chapter_04;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static chapter_04.Attributes.*;

public class ImageImporterImporter implements Importer {

  @Override
  public Document importFile(final File file) throws IOException {
    final Map<String, String> attributes = new HashMap<>();
    attributes.put(PATH, file.getPath());

    final BufferedImage image = ImageIO.read(file);
    attributes.put(WIDTH, String.valueOf(image.getWidth()));
    attributes.put(HEIGHT, String.valueOf(image.getHeight()));
    attributes.put(TYPE, "IMAGE");

    return new Document(attributes);
  }
}
