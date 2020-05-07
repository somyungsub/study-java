package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class MemberJpql {
  @Id
  @GeneratedValue
  Long id;
  private String username;
  private int age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID")
  private TeamJpql teamJpql;

  public void changeTeam(TeamJpql teamJpql) {
    this.teamJpql = teamJpql;
    teamJpql.getMemberJpqls().add(this);
  }
}
