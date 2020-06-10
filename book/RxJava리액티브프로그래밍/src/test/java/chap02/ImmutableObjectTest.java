package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

class ImmutableObjectTest {

  @Test
  @DisplayName("clone 확인")
  public void cloneTest() throws CloneNotSupportedException {

    CloneTest c = new CloneTest("test");
    System.out.println("c = " + c);
    System.out.println("c.clone() = " + c.clone());

  }

  @Test
  @DisplayName("immutable test")
  public void immutable() {
    ImmutableObject i = new ImmutableObject(new Date());
    System.out.println(i);
    System.out.println(i.getValue());

    ImmutableObject i2 = i.changeValue(new Date());
    System.out.println(i2);
    System.out.println(i2.getValue());
  }

  private class CloneTest implements Cloneable{
    private String name;

    public CloneTest(String name) {
      this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
      return super.clone();
    }
  }

}