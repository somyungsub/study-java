package chapter08.ch01_프록시;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Setter @Getter
public class Member {

  @Id
  private Long id;

  private String username;

  @ManyToOne
  private Team team;

}
