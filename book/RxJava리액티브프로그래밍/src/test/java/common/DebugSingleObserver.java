package common;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class DebugSingleObserver<T> implements SingleObserver<T> {

  private String label;

  public DebugSingleObserver() {

  }

  public DebugSingleObserver(String label) {
    this.label = label;
  }

  @Override
  public void onSubscribe(@NonNull Disposable d) {

  }

  @Override
  public void onSuccess(@NonNull Object o) {
    printBasic(o);
  }

  @Override
  public void onError(@NonNull Throwable e) {
    printBasic(e);
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
