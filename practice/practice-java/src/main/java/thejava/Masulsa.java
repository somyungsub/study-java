package thejava;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
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
//    final URI uri = new FileSystemResource("practice/practice-java/target/classes/").getURI();
//    System.out.println("uri = " + uri);
//    Path path = Paths.get(uri);
//    try {
//      new ByteBuddy().redefine(Moja.class)
//          .method(ElementMatchers.named("pullOut"))
//          .intercept(FixedValue.value("Rabbit!!"))
//          .make().saveIn(path.toFile())
//      ;
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    System.out.println(new Moja().pullOut());
  }
}
