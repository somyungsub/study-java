package chapter07.chap03_복합키와식별관계매핑;


import javax.persistence.*;

@Entity
public class Parent2 {

  @EmbeddedId
  private ParentId id; // 식별자 클래스로 인식

  private String name;

}
