package practice.jpashop.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {

  @Id
  @GeneratedValue
  @Column(name = "TEAM_ID")
  private Long id;

  private String name;
}
