package chapter07.chap01_상속관계매핑;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)           // 조인전략, 상속매핑 애노테이션
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)     // 조인전략, 상속매핑 애노테이션
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // 조인전략, 상속매핑 애노테이션
@DiscriminatorColumn(name = "DTYPE")            // 구분 컬럼, 식별자 애노테이션
public abstract class Item {
  @Id
  @GeneratedValue
  @Column(name = "ITEM_ID")
  private Long id;

  private String name;
  private Integer price;

}
