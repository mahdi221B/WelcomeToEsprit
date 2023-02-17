package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Block extends AbstractEntity{
    String name;
    String location;
    String description;
    int numberOfFloor;
    @OneToMany(mappedBy = "block",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Classroom> classrooms;

}
