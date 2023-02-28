package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class AvailablityTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_debut_diso ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_fin_diso ;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private AvailablityDay availablityDays;
}
