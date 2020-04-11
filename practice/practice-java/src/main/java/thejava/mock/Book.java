package thejava.mock;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Book {

  @Id @GeneratedValue
  private Integer id;

  private String title;

  @OneToMany
  private List<Note> notes = new ArrayList<>();

}
