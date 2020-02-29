package design_pattern.kosta.behavior.observer;

public class AnnualSubscriber implements Observer {



  @Override
  public void update(String title, String news) {
    System.out.println("오늘의 뉴스 : " + title + ", " + news);
  }
}
