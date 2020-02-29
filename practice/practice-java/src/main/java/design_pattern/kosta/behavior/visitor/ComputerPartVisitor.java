package design_pattern.kosta.behavior.visitor;

public interface ComputerPartVisitor {

  void visit(Computer computer);

  void visit(Mouse mouse);

  void visit(Monitor monitor);

  void visit(Keyboard keyboard);
}
