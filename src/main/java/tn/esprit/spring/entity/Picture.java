package tn.esprit.spring.entity;


import lombok.Builder;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "picture")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Picture extends AbstractEntity {
    @Column(name = "fileName")
    String filename;
    @Column(name = "filePath")
    String filePath;
    @OneToOne(mappedBy = "picture")
    User user;
}
