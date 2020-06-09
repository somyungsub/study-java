package oop.test;

public interface Father {
  void say();

  default void sayParent() {
    System.out.println("father say!");
  }
}
