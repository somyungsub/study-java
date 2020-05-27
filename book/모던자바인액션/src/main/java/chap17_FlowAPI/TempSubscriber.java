package chap17_FlowAPI;

import java.util.concurrent.Flow.*;

public class TempSubscriber implements Subscriber<TempInfo> {

  private Subscription subscription;

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(TempInfo tempInfo) {
    System.out.println("tempInfo = " + tempInfo);
    subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable) {
    System.err.println(throwable.getMessage());
  }

  @Override
  public void onComplete() {
    System.out.println("Done!!!");
  }
}
