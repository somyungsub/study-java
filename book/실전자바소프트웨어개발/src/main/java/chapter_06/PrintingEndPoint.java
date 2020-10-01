package chapter_06;

public class PrintingEndPoint implements ReceiveEndPoint {
  @Override
  public void onTwoot(final Twoot twoot) {
    System.out.println(twoot.getSenderId() + twoot.getContent());
  }
}
