package chapter07.chap04_조인테이블;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*
  다대다 조인테이블
 */
@Entity
public class Parent3 {
  @Id
  @GeneratedValue
  @Column(name = "PARENT_ID")
  private Long id;
  private String name;

  @ManyToMany
  @JoinTable(name = "PARETN_CHILD",
      joinColumns = @JoinColumn(name = "PARENT_ID"),
      inverseJoinColumns = @JoinColumn(name = "CHILD_ID")
  )
  private List<Child3> child = new ArrayList<>();
}