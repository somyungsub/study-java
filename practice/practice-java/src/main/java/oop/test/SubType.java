package oop.test;

public class SubType extends SuperType {
  @Override
  public void abstractProtected() {
    System.out.println("test protected");
  }

  @Override
  public void abstractPublic() {
    System.out.println("TestPubluc");
  }
}
