package chapter07.chap04_조인테이블;

import javax.persistence.*;


/*
  한 엔티티에 여러 테이블 매핑
  - @Table
    : 기본테이블
    : @Column 을 명시하지 않은 필드는 기본테이블과 매핑됨
  - @SecondaryTable
    : 두번째 테이블, 다른테이블
  - @SecondaryTables
    : 2개이상 테이블을 매핑해야 할 때

   권장 방식은 아니다. -> 테이블 당 엔티티를 만들어서 1:1 매핑하는 것을 권장
 */
@Entity
@Table(name = "BOARD")  // 매핑테이블1
@SecondaryTable(name = "BOARD_DETAIL",  // 매핑테이블2
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "BOARD_DETAIL_ID")
)
//@SecondaryTables({
//    @SecondaryTable(name = "BOARD_DETAIL"),
//    @SecondaryTable(name = "BOARD_FILE")
//})
public class Board {
  @Id
  @GeneratedValue
  private Long id;

  private String title;

  @Column(table = "BOARD_DETAIL") // 매핑할 테이블
  private String content;


}
