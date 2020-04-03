package util;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class VOGenerator {


    private FileSystemResource inPut;
    private FileSystemResource outPut;

    public void setUp(){
      inPut = new FileSystemResource("src/test/resources/in.txt");
      outPut = new FileSystemResource("src/test/resources/out.txt");
    }

    public void transFrontVO() throws IOException {
      Path path = Paths.get(inPut.getURI());
      try(OutputStream out = Files.newOutputStream(path,getOption(outPut))){

      } catch(Exception e){
        e.printStackTrace();
      }
    }

    private StandardOpenOption getOption(FileSystemResource path){
      return path.exists() ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE;
    }


}
