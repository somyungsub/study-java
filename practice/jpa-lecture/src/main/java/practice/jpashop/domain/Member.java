package practice.jpashop.domain;

import lombok.*;

import javax.persistence.*;
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

  private String city;

  private String street;

  private String zipcode;

//  @OneToMany(mappedBy = "member")
//  private List<Order> orders;

  @ManyToOne
  @JoinColumn(name = "TEAM_ID")
  private Team team;


  public Member addTeam(Team team){
    this.team = team;
    team.getMembers().add(this);
    return this;
  }
}
