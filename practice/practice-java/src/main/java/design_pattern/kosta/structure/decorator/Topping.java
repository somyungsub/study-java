package design_pattern.kosta.structure.decorator;

public abstract class Topping implements Cookie{

  protected Cookie cookie;

  // new ab(new abc(new Cookie()))
  public Topping(Cookie cookie) {
    this.cookie = cookie;
  }

  @Override
  public abstract String getName(); // 기능을 추가,제거 확장.. 위임..


}
