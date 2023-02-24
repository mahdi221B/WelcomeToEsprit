package tn.esprit.spring.entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PostMedia extends AbstractEntity{
    @Column(nullable = false)
    String OriginalFilename;
    //Same value as in postman "key" && same value passed in parameters
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String contentType;
    @Lob
    byte[] size;
    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

}