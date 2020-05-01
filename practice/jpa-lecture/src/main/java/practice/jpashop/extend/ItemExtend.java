package practice.jpashop.extend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)         // 조인 전략 : 모든 테이블
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // 단일테이블 전략 : 하나의 테이블에 통합
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // 구현 클래스별 테이블 전략 : 하위 클래스의 갯수 만큼 테이블 생성 : 상황에따라 abstract 삭제
@DiscriminatorColumn// 컬럼명 : default -> DTYPE
public abstract class ItemExtend {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private Integer price;

  @Override
  public String toString() {
    return "ItemExtend{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            '}';
  }
}
