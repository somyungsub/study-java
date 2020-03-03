package chapter07.chap03_복합키와식별관계매핑;

import javax.persistence.*;

@Entity
public class Child {

  @Id
  private String id;

  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "PARENT_ID1", referencedColumnName = "PARENT_ID1"),
      @JoinColumn(name = "PARENT_ID2", referencedColumnName = "PARENT_ID2")
  })
  private Parent parent;
}
