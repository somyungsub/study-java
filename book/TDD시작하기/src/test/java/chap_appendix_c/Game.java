package chap_appendix_c;

public class Game {

  private final GameNumGen genMock;

  public Game(GameNumGen genMock) {
    this.genMock = genMock;
  }

  public void init(GameLevel easy) {
    System.out.println("easy = " + genMock.generate(easy));
  }
}
