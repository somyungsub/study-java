package design_pattern.kosta.behavior.nullobject;

public class NullCustomer extends AbstractCustomer {


  public NullCustomer(String name) {
    this.name = name;
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public String getName() {
    return "Unknown";
  }
}
