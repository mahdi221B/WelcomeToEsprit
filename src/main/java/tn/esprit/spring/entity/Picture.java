package tn.esprit.spring.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "picture")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String filename;
    String filePath;
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
}
