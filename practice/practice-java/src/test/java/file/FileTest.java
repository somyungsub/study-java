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

  @Test
  @DisplayName("parent")
  public void parent() throws IOException {

    String path = "../../../etc/passwd";

    Path path1 = Paths.get(path);
    System.out.println("path1 = " + path1);

    System.out.println("path1.getParent() = " + path1.getParent());
    System.out.println(path1.toUri());
    Path path2 = Paths.get("src/main/resources");
    Path path3 = Paths.get("src/main/resources");

    System.out.println(path1.compareTo(path2));

    System.out.println(path2.compareTo(path3));
    System.out.println("path3.toUri() = " + path3.toUri());

    System.out.println(path2.toFile().getCanonicalPath());
    System.out.println(path3.toFile().getCanonicalPath());

    System.out.println(path2.toFile().getCanonicalPath().compareTo(path3.toFile().getCanonicalPath()));
  }

}