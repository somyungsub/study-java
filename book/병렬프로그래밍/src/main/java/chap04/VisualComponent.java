package chap04;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
  Thread Safe
  - CopyOnWriteArrayList -> 쓰레드 안정성 위임
 */
public class VisualComponent {

  private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
  private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

  public void addKeyListener(KeyListener listener) {
    keyListeners.add(listener);
  }

  public void addMouseListener(MouseListener mouseListener) {
    mouseListeners.add(mouseListener);
  }

  public void removeKeyListener(KeyListener listener) {
    keyListeners.remove(listener);
  }

  public void removeMouseListener(MouseListener listener) {
    mouseListeners.remove(listener);
  }


}
