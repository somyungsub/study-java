package chapter07.chap04_조인테이블;

import javax.persistence.*;

/*
  일대일 조인테이블
 */
@Entity
public class Parent {
  @Id
  @GeneratedValue
  @Column(name = "PARENT_ID")
  private Long id;
  private String name;

  @OneToOne
  @JoinTable(name = "PARENT_CHILD",
      joinColumns = @JoinColumn(name = "PARENT_ID"),
      inverseJoinColumns = @JoinColumn(name = "CHILD_ID")
  )
  private Child child;
}
