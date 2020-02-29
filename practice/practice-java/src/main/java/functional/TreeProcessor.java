package functional;

public class TreeProcessor {
  public static int lookup(String k, int defaultVal, Tree tree) {
    if (tree == null) {
      return defaultVal;
    }

    if (k.equals(tree.key)) {
      return tree.value;
    }

    return lookup(k, defaultVal, k.compareTo(tree.key) < 0 ? tree.left : tree.right);
  }

//  public static void update(String k, int newval, Tree tree) {
//    if (tree == null) {
//    }
//  }

  // 비함수형 -> tree 가 변경에 영향을 받음
  public static Tree update(String k, int newVal, Tree tree) {
    if (tree == null) {
      return new Tree(k, newVal, null, null);
    } else if (k.equals(tree.key)) {
      tree.value = newVal;
    } else if (k.compareTo(tree.key) < 0) {
      tree.left = update(k, newVal, tree.left);
    } else {
      tree.right = update(k, newVal, tree.right);
    }
    return tree;
  }

  // 함수형 -> tree를 새로 생성하여 관리
  public static Tree fupdate(String k, int newVal, Tree tree) {
    return (tree == null) ?
            new Tree(k, newVal, null, null) :
            k.equals(tree.key) ?
                    new Tree(k, newVal, tree.left, tree.right) :
                    k.compareTo(tree.key) < 0 ?
                            new Tree(k, newVal, fupdate(k, newVal, tree.left), tree.right) :
                            new Tree(k, newVal, tree.left, fupdate(k, newVal, tree.right))
            ;

  }

}
