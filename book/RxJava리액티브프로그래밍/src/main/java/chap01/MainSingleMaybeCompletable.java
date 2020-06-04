package chap01;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MainSingleMaybeCompletable {
  public static void main(String[] args) throws InterruptedException {
//    singleExam();
//    maybeExam();
    completableExam();
  }

  public static void singleExam() {

    // 생산자
    Single<DayOfWeek> single = Single.create(emitter -> {
      emitter.onSuccess(LocalDate.now().getDayOfWeek());
    });

    // 구독
    single.subscribe(new SingleObserver<DayOfWeek>() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {

      }

      @Override
      public void onSuccess(@NonNull DayOfWeek dayOfWeek) {
        System.out.println("dayOfWeek = " + dayOfWeek);
      }

      @Override
      public void onError(@NonNull Throwable e) {
        System.out.println("e = " + e);
      }
    });
  }

  public static void maybeExam() {
    // Maybe 생성
//    Maybe<DayOfWeek> maybe = Maybe.create(emitter -> {
//      emitter.onComplete();
//    });
//    Maybe<DayOfWeek> maybe = Maybe.create(emitter -> {
//      emitter.onSuccess(LocalDate.now().getDayOfWeek());
//    });
    Maybe<DayOfWeek> maybe = Maybe.create(emitter -> {
      emitter.onSuccess(LocalDate.now().getDayOfWeek());
      emitter.onComplete();
    });

    // 구독
    maybe.subscribe(new MaybeObserver<DayOfWeek>() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {

      }

      @Override
      public void onSuccess(@NonNull DayOfWeek dayOfWeek) {
        System.out.println("dayOfWeek = " + dayOfWeek);
      }

      @Override
      public void onError(@NonNull Throwable e) {
        System.out.println("e = " + e);
      }

      @Override
      public void onComplete() {
        System.out.println("완료");
      }
    });
  }

  public static void completableExam() throws InterruptedException {
    Completable completable = Completable.create(emitter -> {
      System.out.println("업무로직 수행~!!");
      System.out.println("업무로직 수행~!!");
      System.out.println("업무로직 수행~!!");

      // 완료 통지
      emitter.onComplete();
    });

    // 구독
    completable
        .subscribeOn(Schedulers.computation()) // 비동기실행
        .subscribe(new CompletableObserver() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onComplete() {
            System.out.println("완료");
          }

          @Override
          public void onError(@NonNull Throwable e) {
            System.out.println(e);
          }
        });

    // 잠시대기
    Thread.sleep(100L);
  }
}
