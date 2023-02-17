package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Equipement extends AbstractEntity{
    String type;
    String name;
    String description;
    @Enumerated (EnumType.STRING)
    EquipementStatus status;
    int quantity;
    Date purshaseDate;
    double cost;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Classroom classroom;
}
