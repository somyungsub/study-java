package practice.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class AlbumItem extends Item{

  private String artist;
  private String etc;

}
