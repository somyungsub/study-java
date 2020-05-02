package practice.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn  // default : dtype
public class Item extends BaseEntity{

  @Id
  @GeneratedValue
  @Column(name = "ITEM_ID")
  private Long id;

  private String name;

  private Integer price;

  private Integer stockQuantity;

  @ManyToMany(mappedBy = "items")
  private List<Category> categories = new ArrayList<>();

}
