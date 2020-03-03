package chapter07.chap03_복합키와식별관계매핑;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hello {

  @Id
  private String id;

//  @Id // 실행 시점 오류 발생, 식별자 클래스 만들어서 -> 복합키 구조화
  private String id2;

}
