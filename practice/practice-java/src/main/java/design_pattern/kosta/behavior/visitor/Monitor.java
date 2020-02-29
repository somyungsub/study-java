package design_pattern.kosta.behavior.visitor;

public class Monitor implements ComputerPart{


  @Override
  public void accept(ComputerPartVisitor visitor) {
    visitor.visit(this);
  }
}
