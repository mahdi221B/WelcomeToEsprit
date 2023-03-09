package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Reclamation extends  AbstractEntity{

    @Temporal(TemporalType.DATE)
    @Column(name = "creationDate",nullable = true)

    Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String Description;
    @Enumerated(EnumType.STRING)
    private Type type;
}
