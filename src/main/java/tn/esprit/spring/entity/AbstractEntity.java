package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@FieldDefaults(level= AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    Integer id;
    @CreatedDate
    @Column(name = "creationDate",nullable = false,updatable = false)
    @JsonIgnore
    Date creationDate;
    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    @JsonIgnore
    Date lastUpdateDate;
}
