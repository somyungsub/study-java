package chap19_functional;

public class Tree {
  private String key;
  private int val;
  private Tree left, right;

  public Tree(String key, int val, Tree left, Tree right) {
    this.key = key;
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public String getKey() {
    return key;
  }

  public int getVal() {
    return val;
  }

  public Tree getLeft() {
    return left;
  }

  public Tree getRight() {
    return right;
  }
}
