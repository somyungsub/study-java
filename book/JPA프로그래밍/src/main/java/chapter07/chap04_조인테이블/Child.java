package chapter07.chap04_조인테이블;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Child {

  @Id
  @GeneratedValue
  @Column(name = "CHILD_ID")
  private Long id;

  private String name;

}
