package practice.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

  @Id @GeneratedValue
  @Column(name = "PRODUCT_ID")
  private Long id;
}
