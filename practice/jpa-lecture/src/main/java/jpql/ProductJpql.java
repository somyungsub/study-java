package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class ProductJpql {

  @Id
  @GeneratedValue
  @Column(name = "PRODUCT_ID")
  private Long id;
  private String name;
  private int price;
  private int stockAmount;
}
