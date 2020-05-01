package hellojpa;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Id // pk 매핑
  private Long id;

  @Column(name = "name")  // 컬럼명 name
  private String username;

  @Column(columnDefinition = "int4 default 1 ")
  private Integer age;

  @Column(precision = 18, scale = 3)  // 전체자리수 18, 소수점 3
  private BigDecimal num;

  @Column(columnDefinition = "varchar(100) default 'NONE' not null")
  private String email;

  @Enumerated(EnumType.STRING)      // enum 타입 -> DB에는 없음,
  private RoleType roleType;

  @Enumerated(EnumType.ORDINAL)      // enum 타입 -> DB에는 없음, 디폴트, 절대 운영에서 사용 x -> 테이블 변경에 따른 데이터 저장값은 변경이후만 적용되게 되므로 데이터 정합성 문제발생
  private RoleType roleType2;

  @Temporal(TemporalType.TIMESTAMP) // DB 구분하여 사용 -> DATE, TIME, TIMESTAMP
  private Date createdDate;         // Java는 상관무

  @Temporal(TemporalType.TIME)
  private Date lastModifiedDate;

  @Temporal(TemporalType.DATE)
  private Date deletedDate;

  // Java8 이후, 최신 JPA(5.x) 부터는 -> @Temporal 생략 가능,
  private LocalDateTime deletedDate2; // Date + Time

  private LocalDate deletedDate3;     // Date

  private LocalTime deletedDate4;     // Time

  @Lob  // 대용량 데이터 -> 문자 : CLOB, 그외 : BLOB(바이너리)
  private String description;

  @Lob
  private Byte[] description2;

  @Lob
  private byte[] description3;


  @Transient  // DB 사용, 매핑 x, 어플리케이션 내에서 사용할 필드
  private String temp;
}
