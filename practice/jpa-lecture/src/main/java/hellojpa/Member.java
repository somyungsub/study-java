package hellojpa;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "MBR")
public class Member {

  @Id
  @Column(name = "MBR_ID", unique = true)
  Long id;

  @Column(name = "MBR_AGE")
  int age;
  String name;
  String email;
}
