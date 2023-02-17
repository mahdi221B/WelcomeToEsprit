package tn.esprit.spring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int roomnumber;
    private int floornumber;
    private int capacity;
    private boolean status;
    @OneToMany (mappedBy = "classroom",cascade = CascadeType.ALL)
    private List<Equipement> equipements;

    @ManyToOne
    private Block block;
}
