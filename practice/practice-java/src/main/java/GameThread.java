public class GameThread extends Thread{

  @Override
  public void run() {

    while (true) {
      System.out.println("게임을 합니다");
      System.out.println(GameThread.interrupted());
      Thread.yield();
      System.out.println(GameThread.interrupted());
      if (Thread.interrupted()) {

        System.out.println("die");
        return;
      }
    }
  }
}
