package tn.esprit.spring.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity

public class AvailablityDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisbo;
    @Temporal(TemporalType.DATE)
    private Date date_debut_diso ;
    @Temporal(TemporalType.DATE)
    private Date date_fin_diso ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "availablityDays",cascade = CascadeType.ALL)
    private List<AvailablityTime> availablityTimeList;
}
