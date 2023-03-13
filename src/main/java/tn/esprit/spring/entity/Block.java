package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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
    List<Classroom> classrooms;
}
