package hellojpa;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id
  Long id;
  int age;
  String name;
  String email;
}
