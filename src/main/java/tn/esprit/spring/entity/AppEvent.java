package tn.esprit.spring.entity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Entity
public class AppEvent implements Serializable  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Temporal(TemporalType.DATE)
    private Date ceremonyStartDate;
    @Temporal(TemporalType.DATE)
    private Date ceremonyEndDate;
    @Enumerated(EnumType.STRING)

    private status Status;

    @OneToMany(mappedBy = "event")
    List<Team> teams ;
}
