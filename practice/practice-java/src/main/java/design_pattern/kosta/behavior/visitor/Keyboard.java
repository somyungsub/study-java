package design_pattern.kosta.behavior.visitor;

public class Keyboard implements ComputerPart {
  @Override
  public void accept(ComputerPartVisitor visitor) {
    visitor.visit(this);  // 방문하세요
  }

}
