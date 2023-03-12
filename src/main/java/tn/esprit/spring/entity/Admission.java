package tn.esprit.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private User student;
    @ManyToOne
    private User teacher;
    @ManyToOne
    private Classroom classroom;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private AdmissionResult admissionResult;
    @Enumerated(EnumType.STRING)
    private AdmissionType admissionType;
}
