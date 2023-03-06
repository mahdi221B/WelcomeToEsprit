package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Entity
public class Team   implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String certificate;
    private Double  budget ;
    private  double NoteTeam;



    @ManyToOne
    private AppEvent event ;
    @OneToMany( mappedBy="team")
    private List<User> users ;

}
