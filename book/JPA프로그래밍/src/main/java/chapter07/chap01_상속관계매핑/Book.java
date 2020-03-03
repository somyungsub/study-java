package chapter07.chap01_상속관계매핑;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")

/*
  자식테이블의 기본키 값 변경 (부모테이블의 컬럼명을 그대로 쓰고 싶지 않은 경우 사용)
  - ITEM 기본키 ITEM_ID -> 기본키를 그대로 상속해서 쓰는게 아니라 , BOOK_ID로 변경하여 사용
 */
@PrimaryKeyJoinColumn(name = "BOOK_ID")
public class Book extends Item{
  private String author;
  private String isbn;
}
