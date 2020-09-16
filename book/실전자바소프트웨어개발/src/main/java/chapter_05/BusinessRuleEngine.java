package chapter_05;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessRuleEngine {

  private List<Action> actions;

  public BusinessRuleEngine() {
    this.actions = new ArrayList<>();
  }

  public void addAction(final Action action) {
    actions.add(action);
//    throw new UnsupportedOperationException();
  }

  public int count() {
    return actions.size();
//    throw new UnsupportedOperationException();
  }

  public void run() {

//    throw new UnsupportedOperationException();
  }
}
