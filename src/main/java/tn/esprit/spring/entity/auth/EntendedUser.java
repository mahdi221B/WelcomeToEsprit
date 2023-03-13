package tn.esprit.spring.entity.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class EntendedUser extends User {

    @Getter
    @Setter
    private String identifier;

    public EntendedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public EntendedUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String identifier) {
        super(username, password, authorities);
        this.identifier = identifier;
    }
}
