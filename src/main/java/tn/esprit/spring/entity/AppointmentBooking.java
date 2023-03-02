package tn.esprit.spring.entity;


import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class AppointmentBooking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idR_rdv;
    ///////////////////////////
   /* @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/
    ///////////////////
    @ManyToOne
    @JoinColumn(name = "rendezvous_id")
    private Appointment rdv;
    @Temporal(TemporalType.DATE)
    private Date date_reservation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date Heure_reservation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
