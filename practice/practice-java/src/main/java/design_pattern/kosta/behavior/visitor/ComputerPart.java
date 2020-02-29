package design_pattern.kosta.behavior.visitor;

public interface ComputerPart {

  void accept(ComputerPartVisitor visitor); // 받아주세요
}
