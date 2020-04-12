package thejava.annotation;

public class App {
  public static void main(String[] args) {
    MagicMoja magicMoja = new MagicMoja();
    System.out.println(magicMoja.pullOut());

    Moja moja = new MagicMoja();
    System.out.println(moja.pullOut());
  }
}
