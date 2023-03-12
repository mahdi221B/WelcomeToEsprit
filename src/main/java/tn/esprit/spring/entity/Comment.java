package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    int sentiment;
    LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "id")
    @JsonBackReference //exclude the Post object from the output JSON
    Post post;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    User user;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<React> reactions = new ArrayList<>();
}