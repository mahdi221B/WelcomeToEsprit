package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Profil extends AbstractEntity{
    private String education;
    private String diplomas;
    private String certification;
    private String workExperience;
    private boolean teamcaptain;
    @Enumerated(EnumType.STRING)
    private Intrest  intrest;


    @OneToOne(mappedBy="profil")
    @JsonIgnore

    User user;
}
