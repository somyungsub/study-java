package design_pattern.kosta.structure.decorator;

public class TappingMilk extends Topping {


  @Override
  public String getName() { // 기능 확대구현
    return "후후후" + super.cookie.getName();
  }

  public TappingMilk(Cookie cookie) {
    super(cookie);
  }
}
