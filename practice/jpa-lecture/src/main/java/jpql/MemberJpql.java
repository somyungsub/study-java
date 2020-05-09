package jpql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NamedQueries(
    @NamedQuery(
      name = "Member.findByUsername",
      query = "select m from MemberJpql m where m.username =: username"
    )
)
public class MemberJpql {
  @Id
  @GeneratedValue
  Long id;
  private String username;
  private int age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID")
  private TeamJpql teamJpql;

  @Enumerated(EnumType.STRING)
  private MemberJpqlType memberJpqlType;

  public void changeTeam(TeamJpql teamJpql) {
    this.teamJpql = teamJpql;
    teamJpql.getMemberJpqls().add(this);
  }
}
