package chap04;

import java.util.concurrent.atomic.AtomicInteger;

/*
  Thread Not Safe
  - 쓰레드 안전한 객체를 위임기법으로 사용하더라도 복합연산으로 엮인 경우 -> 위임기법만으로는 해결 안됨
  -> 이런경우, 복합연산 -> 단일연산으로 처리 되도록 동기화 작업 필요
 */
public class NumberRangeNotSafe {

  private final AtomicInteger lower = new AtomicInteger(0);
  private final AtomicInteger upper = new AtomicInteger(0);


  public void setLower(int i) {
    if (i > upper.get()) {
      throw new IllegalArgumentException("can't set lower to " + i + " > upper");
    }
    lower.set(i);
  }

  public void setUpper(int i) {
    if (i < lower.get()) {
      throw new IllegalArgumentException("can't set upper to " + i + " < lower");
    }

    upper.set(i);
  }

  public boolean isInRange(int i) {
    return (i >= lower.get() && i <= upper.get());
  }


}
