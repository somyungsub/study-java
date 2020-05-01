package practice.jpashop.extend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@DiscriminatorValue("M")
public class Movie extends ItemExtend {
  private String direct;
  private String actor;


  @Override
  public String toString() {
    return super.toString()
            + "Movie{" +
            "direct='" + direct + '\'' +
            ", actor='" + actor + '\'' +
            '}';
  }
}
