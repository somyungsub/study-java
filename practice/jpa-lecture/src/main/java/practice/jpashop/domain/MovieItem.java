package practice.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("M")
public class MovieItem extends Item {
  private String director;
  private String actor;
}
