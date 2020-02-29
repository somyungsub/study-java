package design_pattern.kosta.behavior.visitor;

public class ComputerPartAVisitor implements ComputerPartVisitor {


  @Override
  public void visit(Computer computer) {
    System.out.println("aaa");
  }

  @Override
  public void visit(Mouse mouse) {
    System.out.println("aaa");
  }

  @Override
  public void visit(Monitor monitor) {
    System.out.println("aaa");
  }

  @Override
  public void visit(Keyboard keyboard) {
    System.out.println("aaa");
  }
}
