package thejava.mockito.domain;


import lombok.Getter;
import lombok.Setter;
import thejava.mockito.study.StudyStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
public class Study {

  @Id
  @GeneratedValue
  private Long id;

  private StudyStatus status = StudyStatus.DRAFT;

  private int limit;

  private String name;

  private LocalDateTime startDate;

  @ManyToOne
  private Member member;

  public Study(int limit, String name) {
    this.limit = limit;
    this.name = name;
  }

  public Study() {
  }

  public Study(int limit) {
    if (limit < 0) {
      throw new IllegalArgumentException("limit는 0보다 커야한다.");
    }
    this.limit = limit;
  }

  @Override
  public String toString() {
    return "Study{" +
            "status=" + status +
            ", limit=" + limit +
            ", name='" + name + '\'' +
            '}';
  }


  public void setOwner(Member member) {
    this.member = member;
  }

  public void open() {
    this.status = StudyStatus.OPENED;
    this.startDate = LocalDateTime.now();
    System.out.println("Study open !!!");
  }
}
