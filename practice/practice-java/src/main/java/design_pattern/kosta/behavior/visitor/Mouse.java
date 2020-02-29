package design_pattern.kosta.behavior.visitor;

public class Mouse implements ComputerPart {
  @Override
  public void accept(ComputerPartVisitor visitor) {
    visitor.visit(this);
  }


}
