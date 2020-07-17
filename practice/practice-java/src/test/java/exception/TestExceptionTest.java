package exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestExceptionTest {

  @Test
  @DisplayName("Exception")
  public void exception() {
    boolean b = true;
    try {
      if (b) {
        throw new TestException("test");
      }
      System.out.println("111111");
    } catch (Exception e) {
      System.out.println("aaaaa");
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Autoclose")
  public void 오토클로즈(){
    boolean b = true;
    try (ResourceTest resourceTest = new ResourceTest()) {

      if (b) {
        throw new TestRuntimeException("test!!");
      }
      System.out.println("============");
      System.out.println(resourceTest.getName());
      System.out.println(resourceTest.getNum());
      System.out.println("============");
    } catch (Exception e) {
      System.out.println("bbbbbbb");
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Autoclose2")
  public void 오토클로즈2(){
    boolean b = true;
    try  {
      ResourceTest resourceTest = new ResourceTest();
      if (b) {
        throw new TestRuntimeException("test!!");
      }
      System.out.println("============");
      System.out.println(resourceTest.getName());
      System.out.println(resourceTest.getNum());
      System.out.println("============");
    } catch (Exception e) {
      System.out.println("bbbbbbb");
      e.printStackTrace();
    }
  }

}