package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateinterview",nullable = true)

    Date interview;

    private String Interviewer;
    private String Interviewee;
    @Enumerated(EnumType.STRING)
    private Format format;
    private String Feedback;

    @ManyToMany( cascade = CascadeType.ALL)
    private List<Mcqqestion> mcqqestions ;
}

