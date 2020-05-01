package practice.jpashop.extend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@DiscriminatorValue("B")
public class Book extends ItemExtend{
  private String author;
  private String isbn;

  @Override
  public String toString() {
    return super.toString()
            + "Book{" +
            "author='" + author + '\'' +
            ", isbn='" + isbn + '\'' +
            '}';
  }
}
