package tn.esprit.spring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int experience;
    private double salary;
    private String cv;
    private String motivationLetter;
    private String email;
    private String note;
    private String result;
    private int motivationRelevance;
    private double score;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "applicationForm_joboffer",
            joinColumns = @JoinColumn(name = "applicationForm_id"),
            inverseJoinColumns = @JoinColumn(name = "jobOffer_id")
    )
    @JsonIgnore
    private List<JobOffer> jobOffers;



    // Relation many-to-one avec OffreEmploi
/*
    @ManyToMany(mappedBy = "applicationForm")
    private List<JobOffer> jobOffers;*/

}
