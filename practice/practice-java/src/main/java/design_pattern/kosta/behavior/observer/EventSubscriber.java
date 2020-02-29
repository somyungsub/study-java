package design_pattern.kosta.behavior.observer;

public class EventSubscriber implements Observer {

  @Override
  public void update(String title, String news) {
    System.out.println("Event 뉴스 : " + title + ", " + news);
  }

}
