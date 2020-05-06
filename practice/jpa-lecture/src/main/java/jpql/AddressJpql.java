package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
public class AddressJpql {
  private String city;
  private String street;
  private String zipcode;

}
