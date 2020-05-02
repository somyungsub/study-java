package practice.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  // 셀프 참조
  @ManyToOne
  @JoinColumn(name = "PARENT_ID")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> child = new ArrayList<>();

  // 실전에서는 쓰지말것 -> 1:N, N:1로 풀어서 엔티티 만들 것 e.g. OrderItem
  @ManyToMany // 공통 속성 못 받음 -> CATEGORY_ITEM 테이블 생성시 확인
  @JoinTable(name = "CATEGORY_ITEM"
          , joinColumns = @JoinColumn(name = "CATEGORY_ID")
          , inverseJoinColumns = @JoinColumn(name = "ITEM_ID")

  )
  private List<Item> items = new ArrayList<>();

}
