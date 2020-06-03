package chap01;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class MainObservable {

  public static void main(String[] args) throws InterruptedException {
//    observableExample();
    observableDispose();
  }

  public static void observableExample() throws InterruptedException {
    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
      @Override
      public void subscribe(ObservableEmitter<String> emitter) throws Exception {
        String[] datas = {"Hello, World", "안녕, RxJava"};

        for (String data : datas) {
          if (emitter.isDisposed()) {
            return;
          }

          // data  통지
          emitter.onNext(data);
        }

        // 통지 완료
        emitter.onComplete();
      }
    });

    observable
        .observeOn(Schedulers.computation())
        .subscribe(new Observer<String>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(String data) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + data);
          }

          @Override
          public void onError(Throwable e) {
            e.printStackTrace();
          }

          @Override
          public void onComplete() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : 완료");
          }
        });

    Thread.sleep(2000L);
  }

  public static void observableDispose() throws InterruptedException {
    Observable<Long> observable = Observable.interval(200L, TimeUnit.MILLISECONDS);

    observable
        .observeOn(Schedulers.computation())
        .subscribe(new Observer<Long>() {
          private long startTime;
          private Disposable disposable;

          @Override
          public void onSubscribe(Disposable disposable) {
            this.disposable = disposable;
            this.startTime = System.currentTimeMillis();
          }

          @Override
          public void onNext(Long data) {

            if ((System.currentTimeMillis() - startTime) > 1000) {
              System.out.println("구독해지!!");
              disposable.dispose();
              return;
            }

            String name = Thread.currentThread().getName();
            System.out.println(name + " : " + data);
          }

          @Override
          public void onError(Throwable e) {
            e.printStackTrace();
          }

          @Override
          public void onComplete() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " : 완료");
          }
        });

    Thread.sleep(2000L);
  }
}
