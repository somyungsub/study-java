package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class OrderJpql {

  @Id
  @GeneratedValue
  private Long id;
  private int orderAmount;

  @Embedded
  private AddressJpql addressJpql;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private ProductJpql productJpql;
}
