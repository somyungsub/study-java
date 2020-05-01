package practice.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS") // order 예약어관련 에러방지차원
public class Order {

  @Id
  @GeneratedValue
  @Column(name = "ORDER_ID")
  private Long id;

  @Column(name = "MEMBER_ID")
  private Long memberId;  // 주문Id

  private LocalDateTime orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

}
