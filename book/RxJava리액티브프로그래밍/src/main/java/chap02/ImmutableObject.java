package chap02;

import java.util.Date;

public final class ImmutableObject {
  private final Date value;

  public ImmutableObject(Date date) {
    // 가변적인 date가 변경돼도 영향이 없도록 clone 메서드를 활용하여 복제한 겂 설정
    this.value = (Date) date.clone(); // 생성자에서만 값 활당
  }

  public Date getValue() {
    // 반환값 Date가 외부에서 변경돼도 영향이 없도록 복제한 값을 반환
    return (Date)value.clone();
  }

  public ImmutableObject changeValue(Date date) {
    return new ImmutableObject(date); // 새로운 객체를 생성해서 반환
  }
}
