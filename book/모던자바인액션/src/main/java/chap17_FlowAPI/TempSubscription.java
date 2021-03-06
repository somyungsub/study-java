package chap17_FlowAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;

public class TempSubscription implements Subscription {

  private ExecutorService executor = Executors.newSingleThreadExecutor();

  private final Subscriber<? super TempInfo> subscriber;
  private final String town;

  public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
    this.subscriber = subscriber;
    this.town = town;
  }

  @Override
  public void request(long n) {

    executor.submit(() -> {
      for (int i = 0; i < n; i++) {
        try {
          subscriber.onNext(TempInfo.fetch(town));
        } catch (Exception exception) {
          subscriber.onError(exception);
          break;
        }
      }
    });
  }

  @Override
  public void cancel() {
    subscriber.onComplete();
  }
}
