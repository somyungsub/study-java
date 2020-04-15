package thejava.mockito.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Member {

  @Id @GeneratedValue
  private Long id;

  private String name;

  private int age;

  private String email;

  public boolean isSameAge(thejava.annotation.Member member) {
    return this.age == member.getAge();
  }

  public String getEmail() {
    return email;
  }
}
