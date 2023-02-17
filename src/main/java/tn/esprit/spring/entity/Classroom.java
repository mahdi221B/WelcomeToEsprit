package tn.esprit.spring.entity;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Classroom extends AbstractEntity{
    private int roomnumber;
    private int floornumber;
    private int capacity;
    private boolean status;
    @OneToMany (mappedBy = "classroom",cascade = CascadeType.ALL)
    private List<Equipement> equipements;
    @ManyToOne
    private Block block;
}
