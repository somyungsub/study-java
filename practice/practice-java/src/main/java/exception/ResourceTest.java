package exception;

import lombok.Data;

@Data
public class ResourceTest implements AutoCloseable{
  private String name;
  private int num;

  @Override
  public void close() throws Exception {
    System.out.println("name = " + name);
    System.out.println("num = " + num);
  }
}
