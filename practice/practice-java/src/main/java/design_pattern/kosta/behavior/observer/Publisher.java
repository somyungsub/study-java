package design_pattern.kosta.behavior.observer;

/* Subject Interface */
public interface Publisher {


  // 관리하고 등록하는 기능
  void add(Observer observer);

  void delete(Observer observer);

  void notifyObserver();

}
