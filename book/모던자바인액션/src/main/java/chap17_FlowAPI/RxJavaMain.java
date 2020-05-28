package chap17_FlowAPI;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class RxJavaMain {
  public static void main(String[] args) {
//    tempBasic();
//    getRxJavaObservable();
    getRxJavaObservable2();
  }

  private static void getRxJavaObservable2() {
    Observable<TempInfo> observable = getCelsiusTemperatures("New York", "Chicago", "San Fransisco");
    observable.blockingSubscribe(new TempObserver());
  }

  private static void getRxJavaObservable() {
    Observable<TempInfo> observable = getTemperature("New York");
    observable.blockingSubscribe(new TempObserver());
  }

  private static void tempBasic() {
    Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
    onePerSec.blockingSubscribe(i -> System.out.println(TempInfo.fetch("New York")));
  }

  private static Observable<TempInfo> getTemperature(String town) {
    return Observable.create(emitter -> {
      Observable.interval(1, TimeUnit.SECONDS)
              .subscribe(i -> {
                if (!emitter.isDisposed()) {
                  if (i >= 5) {
                    emitter.onComplete();
                  } else {
                    try {
                      emitter.onNext(TempInfo.fetch(town));
                    } catch (Exception e) {
                      emitter.onError(e);
                    }
                  }
                }
              });
    });
  }

  public static Observable<TempInfo> getCelsiusTemperature(String town) {
    return getTemperature(town)
            .map(tempInfo -> new TempInfo(tempInfo.getTown(), (tempInfo.getTemp() - 32) * 5 / 9));
  }

  public static Observable<TempInfo> getNegativeTemperature(String town) {
    return getCelsiusTemperature(town)
            .filter(tempInfo -> tempInfo.getTemp() < 0);
  }

  public static Observable<TempInfo> getCelsiusTemperatures(String... town) {
    return Observable.merge(
            Arrays.stream(town)
                    .map(RxJavaMain::getCelsiusTemperature)
                    .collect(toList())
    );
  }

}
