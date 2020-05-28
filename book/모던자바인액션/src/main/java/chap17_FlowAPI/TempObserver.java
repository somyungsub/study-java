package chap17_FlowAPI;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class TempObserver implements Observer<TempInfo> {

  @Override
  public void onSubscribe(@NonNull Disposable d) {
  }

  @Override
  public void onNext(@NonNull TempInfo tempInfo) {
    System.out.println(tempInfo);
  }

  @Override
  public void onError(@NonNull Throwable e) {
    System.out.println("Got Problem : " + e.getMessage());
  }

  @Override
  public void onComplete() {
    System.out.println("Done!!!");
  }
}
