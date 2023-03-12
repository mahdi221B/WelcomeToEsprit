package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

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
    @JsonBackReference
    Post post;

    @ManyToOne
    Comment comment;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    User user;
}
