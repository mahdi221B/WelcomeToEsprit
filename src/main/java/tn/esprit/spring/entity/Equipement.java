package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String name;
    private String description;

    @Enumerated (EnumType.STRING)
    private EquipementStatus status;
    private int quantity;
    private Date purshaseDate;
    private double cost;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Classroom classroom;
}
