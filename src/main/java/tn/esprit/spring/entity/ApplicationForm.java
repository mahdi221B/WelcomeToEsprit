package tn.esprit.spring.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class ApplicationForm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormCand;

    private String name;
    private String experience;
    private double salary;
    private String cv;
    private String motivationLetter;
    private String email;
    private String note;
    private String result;


    // Relation many-to-one avec OffreEmploi

    @ManyToMany(mappedBy = "formulairesCandidature")
    private List<JobOffer> offresEmplois;

}
