package tn.esprit.spring.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Profil extends AbstractEntity{
    private String education;
    private String diplomas;
    private String certification;
    private String workExperience;
}
