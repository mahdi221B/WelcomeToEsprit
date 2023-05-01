package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
@FieldDefaults(level= AccessLevel .PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "creationDate",nullable = true)
    @JsonIgnore
    Date creationDate;
    @Column(name = "lastModifiedDate")
    @JsonIgnore
    Date lastUpdateDate;
}
