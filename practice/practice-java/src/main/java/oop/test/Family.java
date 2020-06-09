package oop.test;

public class Family implements Father, Mother {

  @Override
  public void say() {
    System.out.println("Family!!");
  }

  @Override
  public void sayParent() {
    System.out.println("say Parent Family!!");
  }
}
