package thejava.mock;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Note {

  @Id
  @GeneratedValue
  private Integer id;

  private String text;


}
