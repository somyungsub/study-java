package practice.jpashop.extend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@DiscriminatorValue("A")
public class Album extends ItemExtend{

  private String artist;

  @Override
  public String toString() {
    return super.toString()
            + "Album{" +
            "artist='" + artist + '\'' +
            '}';
  }
}
