package thejava.annotation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Member {

  private String name;

  private int age;

  public boolean isSameAge(Member member) {
    return this.age == member.getAge();
  }


}
