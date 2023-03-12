package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String education;
    private String diplomas;
    private String certification;
    private String workExperience;
    @Enumerated(EnumType.STRING)
    private StudentLevel studentLevel;
    @Enumerated(EnumType.STRING)
    private Interest interest;
    private boolean teamCaptain;
    @OneToOne(mappedBy="profil")
    @JsonIgnore
    User user;

}
