package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private StudentLevel studentLevel;
    @Enumerated(EnumType.STRING)
    private Interest interest;
    private boolean teamCaptain;
    @OneToOne(mappedBy="profil")
    @JsonIgnore
    User user;
}
