package tn.esprit.spring.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.auth.EntendedUser;
import tn.esprit.spring.exception.EntityNotFoundException;
import tn.esprit.spring.exception.ErrorCodes;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository,RoleRepository roleRepository){
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=userRepository.findUserByEmailAddressContains(email).orElseThrow(
                ()->new EntityNotFoundException("No User with email= "+email+"was found in DB",
                        ErrorCodes.USER_NOT_FOUND
                )
        );
        List<Role> roles=new ArrayList<>();
        roles=roleRepository.findRolesByUsers(user);

        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        roles.forEach(role->authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new EntendedUser(user.getEmailAddress(),user.getPassword(),authorities);
       // return new EntendedUser("foulen","foulen", Collections.emptyList());
    }
}
