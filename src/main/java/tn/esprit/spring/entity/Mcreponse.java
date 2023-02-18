package tn.esprit.spring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Mcreponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String text;
    @Enumerated(EnumType.STRING)
    private Confidence confidence;

    @ManyToMany(mappedBy = "mcreponseList",cascade = CascadeType.ALL)
    private List<Mcqqestion> mcqqestionList ;
}