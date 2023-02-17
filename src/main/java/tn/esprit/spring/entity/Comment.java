package tn.esprit.spring.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends AbstractEntity {
    String content;
    @Temporal(TemporalType.DATE)
    Date createdAt;
    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "id")
    Post post;
    @ManyToMany(mappedBy = "comments", cascade = CascadeType.ALL)
    List<React> reactions;
}