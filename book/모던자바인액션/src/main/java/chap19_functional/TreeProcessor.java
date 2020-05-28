package chap19_functional;

public class TreeProcessor {
  public static int lookup(String k, int defaultVal, Tree tree) {
    if (tree == null) {
      return defaultVal;
    }

    if (k.equals(tree.getKey())) {
      return tree.getVal();
    }

    return lookup(k, defaultVal, k.compareTo(tree.getKey()) < 0 ? tree.getLeft() : tree.getRight());
  }
}
