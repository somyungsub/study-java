package practice.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 추상클래스 권장
@Setter
@Getter
public abstract class BaseEntity2 {

  @Id @GeneratedValue
  private Long tid;

  @Column(name = "CREATE_MEMBER")
  private String createdBy;
  private LocalDateTime createdDate;

}
