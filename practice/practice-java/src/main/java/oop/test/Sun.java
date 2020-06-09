package oop.test;

public class Sun implements Father, Mother {

  @Override
  public void say() {
    System.out.println("mother~ love you~~");
  }

  @Override
  public void sayParent() {
    System.out.println("okok Sun~");
  }
}
