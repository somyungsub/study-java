package file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileTest {

  @Test
  @DisplayName("paths")
  public void paths() throws IOException {
    Path etc = Paths.get("/");
    System.out.println(etc.getRoot());

    Files.walk(etc, 1)
    .forEach(path -> System.out.println("path = " + path));
  }

}