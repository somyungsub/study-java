package design_pattern.kosta.behavior.visitor;

public class Computer implements ComputerPart {

  private ComputerPart[] parts;

  public Computer() {
    parts = new ComputerPart[]{new Mouse(), new Monitor(), new Keyboard()};

  }

  @Override
  public void accept(ComputerPartVisitor visitor) {

    for (ComputerPart part : parts) {
      part.accept(visitor);
    }
    visitor.visit(this);
  }


}
