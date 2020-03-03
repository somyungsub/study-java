package chapter07.chap03_복합키와식별관계매핑;

import java.io.Serializable;


/*
   ID 식별자 클래스
 */
public class ParentId implements Serializable {

  private String id1; //Parent.id1 매핑
  private String id2; //Parent.id2 매핑

  public ParentId() {
  }

  public ParentId(String id1, String id2) {
    this.id1 = id1;
    this.id2 = id2;
  }
}
