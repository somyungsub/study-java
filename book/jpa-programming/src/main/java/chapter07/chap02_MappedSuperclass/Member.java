package chapter07.chap02_MappedSuperclass;

import javax.persistence.Entity;

@Entity
public class Member extends BaseEntity {

  // id, name 상속

  private String email;
}
