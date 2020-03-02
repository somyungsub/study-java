package chapter07.chap02_MappedSuperclass;

import javax.persistence.Entity;

@Entity
public class Seller extends BaseEntity{

  // id, name 상속
  private String shopName;

}
