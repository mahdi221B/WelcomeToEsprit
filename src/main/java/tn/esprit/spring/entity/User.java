package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    List<Role> roles;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<Post> posts;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<React> reacts;



    @OneToOne

    Profil  profil ;

    @ManyToOne

    private Team team ;





}
