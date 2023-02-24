package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbstractEntity{
    @Column(name = "rolename")
    String roleName;
    @ManyToMany(mappedBy ="roles", cascade = CascadeType.ALL)
    @JsonIgnore
    List<User> users =  new ArrayList<>();;
}
