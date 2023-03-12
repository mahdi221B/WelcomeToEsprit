package tn.esprit.spring.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull(message = "Format cannot be null")
    private String specialty;
    @NotNull(message = "Format cannot be null")
    private String title;
    @NotNull(message = "Format cannot be null")
    @Size(min = 6, max = 20, message = "Feedback must be between 20 and 300 characters")
    private String description;
    @NotNull(message = "Format cannot be null")
    private String requirements;
    @NotNull(message = "Format cannot be null")
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
