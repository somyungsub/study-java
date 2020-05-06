package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class TeamJpql {

  @Id
  @GeneratedValue
  @Column(name = "TEAM_ID")
  Long id;
  private String name;

  @OneToMany(mappedBy = "teamJpql")
  private List<MemberJpql> memberJpqls;
}
