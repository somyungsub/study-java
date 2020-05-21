public class Main {
  public static void main(String[] args) {
    Thread gameThread = new GameThread();
    gameThread.setDaemon(true);
    gameThread.start();
    Thread.yield();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
