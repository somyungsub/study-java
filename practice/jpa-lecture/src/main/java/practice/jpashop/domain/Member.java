package practice.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  private String username;

  // 기간 Period
  @Embedded
  private Period workPeriod;

  // 주소
  @Embedded
  private Address homeAddress;

  // 주소 -> 컬럼 중복의 경우 설정 필요 (@AttributeOverrides)
  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE")),
          @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
          @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET"))
  })
  private Address workAddress;

//  @OneToMany(mappedBy = "member")
//  private List<Order> orders;

  @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩, defalut : EAGER(같이 자주 사용하는 경우 성능최적화 대상)
  @JoinColumn(name = "TEAM_ID")
  private Team team;

  @OneToOne
  @JoinColumn(name = "LOCKER_ID")
  private Locker locker;

  @ManyToMany
  @JoinTable(name = "MEMBER_PRODUCT")
  private List<Product> products;


  public Member addTeam(Team team){
    this.team = team;
    team.getMembers().add(this);
    return this;
  }
}
