package design_pattern.kosta.structure.decorator;


public class TappingChocolate extends Topping {

  @Override
  public String getName() {
    return "초코초코" + super.cookie.getName();
  }

  public TappingChocolate(Cookie cookie) {
    super(cookie);
  }
}
