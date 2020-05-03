package practice.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 추상클래스 권장
@Setter
@Getter
public abstract class SuperBaseEntity {

  @Column(name = "CANCEL_MEMBER")
  private String canceledBy;
  private LocalDateTime canceledDate;

}
