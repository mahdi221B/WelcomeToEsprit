package tn.esprit.spring.entity;

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
    private Interest interest;
    private Double note;
    private Double finalNote;

}
