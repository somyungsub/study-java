package common;

import io.reactivex.subscribers.DisposableSubscriber;

public class DebugSubscriber<T> extends DisposableSubscriber<T> {

  private String label;

  public DebugSubscriber() {
    super();
  }

  public DebugSubscriber(String label) {
    super();
    this.label = label;
  }

  @Override
  public void onNext(T data) {
    printBasic(data);
  }

  @Override
  public void onError(Throwable throwable) {
    printBasic(throwable);
  }


  @Override
  public void onComplete() {
    printBasic("완료!!");
  }

  private void printBasic(Object t) {
    String name = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(name + ": " + t);
    } else {
      System.out.println(name + ": " + label + ": " + t);
    }
  }
}
