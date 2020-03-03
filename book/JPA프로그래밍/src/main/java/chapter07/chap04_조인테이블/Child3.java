package chapter07.chap04_조인테이블;

import javax.persistence.*;

@Entity
public class Child3 {

  @Id
  @GeneratedValue
  @Column(name = "CHILD_ID")
  private Long id;

  private String name;

}
