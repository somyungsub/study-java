package chapter07.chap02_MappedSuperclass;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
  @MappedSuperclass
  - 테이블과는 무관
  - 엔티티가 공통으로 사용하는 매핑정보를 모아주는 역할을 할 뿐
 */
@MappedSuperclass
public abstract class BaseEntity {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

}
