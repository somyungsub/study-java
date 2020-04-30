package hellojpa;

import lombok.*;

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
@Table(name = "MBR")
public class Member {

  @Id
  Long id;
  int age;
  String name;
  String email;
}
