package practice.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery2 extends BaseEntity2{


  private String city;

  private String street;

  private String zipcode;

  private DeliveryStatus status;

}
