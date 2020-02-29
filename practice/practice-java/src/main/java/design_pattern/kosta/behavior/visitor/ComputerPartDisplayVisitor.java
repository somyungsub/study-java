package design_pattern.kosta.behavior.visitor;

public class ComputerPartDisplayVisitor implements ComputerPartVisitor {
  @Override
  public void visit(Computer computer) {
    System.out.println("computer");

    // computer 자료를 이용해서 기능을 구현... 검색기능...
  }

  @Override
  public void visit(Mouse mouse) {
    System.out.println("mouse");
  }

  @Override
  public void visit(Monitor monitor) {
    System.out.println("monitor");
  }

  @Override
  public void visit(Keyboard keyboard) {
    System.out.println("keyboard");
  }
}
