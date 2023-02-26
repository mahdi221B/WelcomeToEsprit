package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private String description;
    private int numberOfFloor;

    @OneToMany(mappedBy = "block",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Classroom> classrooms;

}
