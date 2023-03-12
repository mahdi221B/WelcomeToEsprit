package tn.esprit.spring.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class Address {
    @Column(name = "address1")
    String address1;
    @Column(name = "address2")
    String address2;
    @Column(name = "city")
    String city;
    @Column(name = "codepostal")
    String codePostal;
    @Column(name = "country")
    String country;
}