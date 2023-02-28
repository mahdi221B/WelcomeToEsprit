package tn.esprit.spring.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity

public class Availablity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisbo;

    @Temporal(TemporalType.DATE)
    private Date date_debut_diso ;
    @Temporal(TemporalType.DATE)
    private Date date_fin_diso ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
