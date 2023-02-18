package tn.esprit.spring.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class JobOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffre;


    private String specialty;
    private String title;
    private String description;
    private String requirements;
    private String location;
    @Temporal(TemporalType.DATE)
    private Date date_limite;

    @ManyToMany
    @JoinTable(
            name = "applicationForm_JobOffer",
            joinColumns = @JoinColumn(name = "jobOffer_id"),
            inverseJoinColumns = @JoinColumn(name = "applicationForm_id")
    )
    private List<ApplicationForm> formulairesCandidature;

}
