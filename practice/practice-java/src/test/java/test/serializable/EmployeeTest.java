package test.serializable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class EmployeeTest {

  private String pathname = "practice/practice-java/src/main/resources/serial.txt";

  @Test
  @DisplayName("직렬화")
  public void serializable() throws IOException {
    File file = new File(pathname);
    ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(file.toURI()), StandardOpenOption.WRITE));

    Employee ssosso = new Employee("ssosso", 33);
    Employee ssosso2 = new Employee("ssosso2", 332);
    Employee ssosso3 = new Employee("ssosso3", 333);
    out.writeObject(ssosso);
    out.writeObject(ssosso2);
    out.writeObject(ssosso3);
  }

  @Test
  @DisplayName("역직렬화")
  public void deserializable() throws IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(Files.newInputStream(new File(pathname).toPath()));

    Employee e1 = (Employee) in.readObject();
    Employee e2 = (Employee) in.readObject();
    Employee e3 = (Employee) in.readObject();

    System.out.println("e1 = " + e1);
    System.out.println("e2 = " + e2);
    System.out.println("e3 = " + e3);
  }

}