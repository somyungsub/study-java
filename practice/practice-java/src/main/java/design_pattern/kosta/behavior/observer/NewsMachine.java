package design_pattern.kosta.behavior.observer;

import java.util.ArrayList;

public class NewsMachine implements Publisher {

  private ArrayList<Observer> observers;
  private String title;
  private String news;

  public NewsMachine() {
    observers = new ArrayList<>();
  }

  @Override
  public void add(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void delete(Observer observer) {
    int index = observers.indexOf(observer);
    observers.remove(index);
  }

  @Override
  public void notifyObserver() {
    for (Observer observer : observers) {
      observer.update(title, news);
    }
  }

  // 상태변경 -> noti -> update
  public void setNews(String title, String news) {
    this.title = title;
    this.news = news;
    notifyObserver();
  }
}
