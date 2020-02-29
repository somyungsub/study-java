package design_pattern.kosta.structure.flyweight;

public class Flyweight {

  private String data;  // share data -> flyweight라고 부름.

  public Flyweight(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }

}
