package chap01;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainObservable {

  public static void main(String[] args) throws InterruptedException {
    observableExample();
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
}
