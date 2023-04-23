package tn.esprit.spring.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@Entity
@Table(name = "verificationToken")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerificationToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "token")
    String token;
    @Column(name = "expiry_date")
    Timestamp expiryDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public VerificationToken() {
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public VerificationToken(Long id, String token, Timestamp expiryDate, User user) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
