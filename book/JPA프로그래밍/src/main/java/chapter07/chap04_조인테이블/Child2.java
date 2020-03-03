package chapter07.chap04_조인테이블;

import javax.persistence.*;

@Entity
public class Child2 {

  @Id
  @GeneratedValue
  @Column(name = "CHILD_ID")
  private Long id;

  private String name;

  @ManyToOne(optional = false)
  @JoinTable(name = "PARENT_CHILD",
      joinColumns = @JoinColumn(name = "CHILD_ID"),
      inverseJoinColumns = @JoinColumn(name = "PARENT_ID")
  )
  private Parent parent;
}
