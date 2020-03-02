package chapter07.chap01_상속관계매핑;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")  // 서브타입, DB에 저장되는 값 -> A
public class Album extends Item{
  private String artist;
}
