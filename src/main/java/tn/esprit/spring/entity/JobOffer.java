package tn.esprit.spring.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    @ManyToMany(mappedBy = "jobOffers")
    private List<ApplicationForm> applicationForms;




   /* @ManyToMany
    @JoinTable(
            name = "applicationForm_JobOffer",
            joinColumns = @JoinColumn(name = "jobOffer_id"),
            inverseJoinColumns = @JoinColumn(name = "applicationForm_id")
    )
    private List<ApplicationForm> applicationForm;
*/

}
