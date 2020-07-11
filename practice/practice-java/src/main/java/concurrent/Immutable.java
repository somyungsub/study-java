package concurrent;

public class Immutable {
  private final int a;
  private final String s;

  public Immutable(int a, String s) {
    this.a = a;
    this.s = s;
  }

  public int getA() {
    return a;
  }

  public String getS() {
    return s;
  }

}
