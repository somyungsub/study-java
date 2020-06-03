package chap04_DP적용전략.sec01;

import chap04_DP적용전략.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-13 error(throwable) 예제 */
public class ErrorSample<T> {
  
  public static void main(String[] args) {
    
    Flowable
        // 에러를 통지하는 Flowable을 생성한다
        .error(new Exception("예외 발생"))
        // 구독을 시작한다
        .subscribe(new DebugSubscriber<>());
  }
  
}
