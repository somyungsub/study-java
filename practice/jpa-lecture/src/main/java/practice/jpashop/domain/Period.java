package practice.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Period {

  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
