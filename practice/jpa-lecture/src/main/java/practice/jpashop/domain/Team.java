package practice.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Team {

  @Id
  @GeneratedValue
  @Column(name = "TEAM_ID")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "team") // 연결된 제네릭 타입의 필드명
  private List<Member> members = new ArrayList<>();
}
