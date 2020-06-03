package chap04_DP적용전략.sec01;

import chap04_DP적용전략.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-14 never() 예제 */
public class NeverSample<T> {
  
  public static void main(String[] args) {
    
    Flowable
        // 아무것도 통지하지 않는 Flowable을 생성한다
        .never()
        // 구독을 시작한다
        .subscribe(new DebugSubscriber<>());
  }
  
}
