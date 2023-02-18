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
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRnv;
    private String titre;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateRnv;

    @OneToMany(mappedBy = "rdv")
    private List<AppointmentBooking> reservations;
}
