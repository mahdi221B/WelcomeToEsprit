package tn.esprit.spring.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class React extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    ReactType reaction;
    @ManyToOne
    Post post;
    @ManyToOne
    Comment comment;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    User user;
}
