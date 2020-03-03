package chapter07.chap04_조인테이블;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*
  다대일 조인테이블
 */
@Entity
public class Parent2 {
  @Id
  @GeneratedValue
  @Column(name = "PARENT_ID")
  private Long id;
  private String name;

  @OneToMany(mappedBy = "parent")
  private List<Child2> child = new ArrayList<>();
}