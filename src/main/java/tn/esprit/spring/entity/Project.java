package tn.esprit.spring.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Entity
public class Project    implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String video;
    private String presentation;
    private String description;
    private Date submitDate;

    @OneToOne
    private Team  team;

    @OneToMany(mappedBy = "project")
    List<Note> notes;


}
