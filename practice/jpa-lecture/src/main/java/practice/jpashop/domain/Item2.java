package practice.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item2 extends BaseEntity2{

  private String name;

  private Integer price;

  private Integer stockQuantity;

}
