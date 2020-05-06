package practice.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  // 값 타입 컬렉션 -> 쓰지말것. 추적 불가, 성능저하 등 ... 특징적 제약사항
  @ElementCollection
  @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID"))
  @Column(name = "FOOD_NAME")
  private Set<String> favoriteFoods = new HashSet<>();

  //  @ElementCollection
//  @CollectionTable(name = "ADDRESS_HISTORY", joinColumns = @JoinColumn(name = "MEMBER_ID"))
//  private List<Address> addressHistory = new ArrayList<>();

  // 위 대안
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "MEMBER_ID")
  private List<AddressEntity> addressHistory = new ArrayList<>();

  @Column(length = 450)
  private String test;


  private static final String fixedLength = "446";

  public Member addTeam(Team team){
    this.team = team;
    team.getMembers().add(this);
    return this;
  }
}
