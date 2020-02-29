package design_pattern.kosta.structure.composite;

import java.util.ArrayList;
import java.util.List;

public interface Node {
  public String getName();
}


class File implements Node {

  private String name;

  @Override
  public String getName() {
    return this.name;
  }
}

class Directory implements Node {
  private String name;
  List<Node> childList;

  @Override
  public String getName() {
    return this.name;
  }

  public void add(Node node) {
    if (childList == null) {
      childList = new ArrayList<>();
    }
    childList.add(node);
    System.out.println("Node 삽입");

  }
}