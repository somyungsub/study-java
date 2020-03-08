package chapter08.ch01_프록시;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter @Getter
public class Team {

  @Id
  private Long id;

  private String name;

}
