package design_pattern.kosta.behavior.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {

  private List<Memento> mementos = new ArrayList<>();



  // 저장
  public void add(Memento memento) {
    mementos.add(memento);
  }

  // 꺼내기
  public Memento getMemento(int index) {
    return mementos.get(index);
  }

}
