package tn.esprit.spring.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Post extends AbstractEntity {
    String title;
    String content;
    String tags;
    int sentimentScore;
    LocalDateTime createdAt;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    List<React> reactions = new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PostMedia> media = new ArrayList<>();
}