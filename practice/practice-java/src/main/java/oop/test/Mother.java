package oop.test;

public interface Mother {
  void say();

  default void sayParent() {
    System.out.println("mother say!");
  }
}
