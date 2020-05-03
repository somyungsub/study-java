package practice.jpashop.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
@Setter
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Address {

  private String city;
  private String street;

  @Column(name = "ZIP_CODE")
  private String zipcode;

//  @OneToOne
//  private Phone member;

}
