package practice.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 추상클래스 권장
public abstract class BaseEntity {

  @Column(name = "CREATE_MEMBER")
  private String createdBy;
  private LocalDateTime createdDate;

  @Column(name = "DELETE_MEMBER")
  private String modifiedBy;
  private LocalDateTime modifiedDate;
}
