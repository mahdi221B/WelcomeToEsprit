package tn.esprit.spring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.List;
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
public class Mcq implements Serializable{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idMcq;
        private String McqTitle;
        private int Duration;

        @ManyToMany(cascade = CascadeType.ALL)
        @JsonIgnore
        private List<Question> questions;





}
