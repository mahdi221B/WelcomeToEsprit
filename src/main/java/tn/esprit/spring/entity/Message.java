package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt = new Date();
    boolean deleted = false;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Conversation conversation;
    @ManyToOne(cascade = CascadeType.ALL)
    User sender;
}