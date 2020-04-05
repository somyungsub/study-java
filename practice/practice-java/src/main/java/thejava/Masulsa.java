package thejava;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Masulsa {

  public static void main(String[] args) throws IOException {
    // byte buddy
    // bytebuddy.net

    final ClassLoader classLoader = Masulsa.class.getClassLoader();
    TypePool typePool = TypePool.Default.of(classLoader);

    final URI uri = new FileSystemResource("practice/practice-java/target/classes/").getURI();
    Path path = Paths.get(uri);

    try {
      new ByteBuddy().redefine(
              typePool.describe("thejava.Moja").resolve(),
              ClassFileLocator.ForClassLoader.of(classLoader)
          )
          .method(ElementMatchers.named("pullOut"))
          .intercept(FixedValue.value("Rabbit!!"))
          .make().saveIn(path.toFile())
      ;
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(new Moja().pullOut());
  }
}
