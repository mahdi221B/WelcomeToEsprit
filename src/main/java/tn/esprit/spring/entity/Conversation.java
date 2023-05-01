package tn.esprit.spring.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Conversation extends AbstractEntity {

    @ManyToMany
    @JoinTable(
            name = "conversation_participants",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> participants = new ArrayList<>();
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    List<Message> messages = new ArrayList<>();
}