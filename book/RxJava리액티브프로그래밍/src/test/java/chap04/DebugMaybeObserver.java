package chap04;

import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class DebugMaybeObserver<T> implements MaybeObserver<T> {

  private String label;

  public DebugMaybeObserver() {
  }
  public DebugMaybeObserver(String label) {
    this.label = label;

  }

  @Override
  public void onSubscribe(@NonNull Disposable d) {

  }

  @Override
  public void onSuccess(@NonNull T o) {
    printBasic(o);
  }

  @Override
  public void onError(@NonNull Throwable e) {
    printBasic(e);
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
