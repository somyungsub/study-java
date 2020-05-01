package practice.jpashop.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
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

  @ManyToOne
  @JoinColumn(name = "TEAM_ID")
  private Team team;



}
