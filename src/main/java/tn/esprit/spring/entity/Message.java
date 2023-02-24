package tn.esprit.spring.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Message extends AbstractEntity{
    String content;
    @ManyToOne(cascade = CascadeType.ALL)
    Conversation conversation;
    @ManyToOne(cascade = CascadeType.ALL)
    User sender;
}