package practice.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class BookItem  extends Item{
  private String author;
  private String isbn;
  private Integer price;
}
