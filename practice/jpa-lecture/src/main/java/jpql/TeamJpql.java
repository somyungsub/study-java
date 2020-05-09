package jpql;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
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

//  @BatchSize(size = 100)  // In 쿼리로 100개 까지 넘김 -> 쿼리 실행 갯수
  @OneToMany(mappedBy = "teamJpql")
  private List<MemberJpql> memberJpqls = new ArrayList<>();

}
