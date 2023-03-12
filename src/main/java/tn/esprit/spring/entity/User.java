package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity{
    @Column(name = "lastname")
    String lastName;
    @Column(name = "firstname")
    String firstName;
    @Column(name = "emailaddress")
    String emailAddress;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    Date birthDate;
    @Column(name = "password")
    String password;
    @Embedded
    Address address;
    @Column(name = "picture")
    String picture;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Post> posts  = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<React> reacts = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Interview> interviewList;
}
