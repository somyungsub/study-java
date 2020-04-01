import org.junit.Test;
import org.springframework.core.io.FileSystemResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;

public class UnitTest {

  @Test
  public void 파일변환_교육정리() throws Exception{
    FileSystemResource input = new FileSystemResource("src/main/resources/edu2.txt");
    FileSystemResource output = new FileSystemResource("src/main/resources/eduOut2.txt");
    Path inPath = Paths.get(input.getURI());
    final Path outPath = Paths.get(output.getPath());

    try (BufferedReader reader = Files.newBufferedReader(inPath);
         final OutputStream stream = Files.newOutputStream(outPath, StandardOpenOption.CREATE)) {

      reader.lines()
              .map(s -> s.replace("-", ""))
              .map(s -> s.trim().replace(" ", ""))
              .map(s -> s.trim().replace("\t", ""))
              .map(s -> s.replaceAll("/", ","))
              .forEach(s -> {
                try {
                  stream.write(s.getBytes());
                  stream.write(System.lineSeparator().charAt(0));
                } catch (IOException e) {
                  e.printStackTrace();
                }
              })
      ;
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
