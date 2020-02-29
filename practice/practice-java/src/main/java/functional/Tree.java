package functional;

public class Tree {
  String key;
  int value;
  Tree left, right;

  public Tree(String key, int value, Tree left, Tree right) {
    this.key = key;
    this.value = value;
    this.left = left;
    this.right = right;
  }
}
