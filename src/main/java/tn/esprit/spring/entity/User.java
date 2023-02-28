package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String motDePasse;




    @OneToMany(mappedBy = "user")
    private List<AppointmentBooking> appointmentBookings;

    // Relation one-to-one avec Formulaire candidature
    @OneToOne(mappedBy = "user")
    private ApplicationForm applicationForm;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Availablity> availablities = new ArrayList<>();

}
