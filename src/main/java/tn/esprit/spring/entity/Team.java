package tn.esprit.spring.entity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
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


    @OneToOne
    private Project project;

    @ManyToOne
    private AppEvent event ;

}
