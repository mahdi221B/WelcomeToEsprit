package tn.esprit.spring.services;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.stereotype.Service;
import tn.esprit.spring.dto.UserDto;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

import java.util.List;
public interface UserService {
        public User addUser(User user) throws  javax.mail.MessagingException;
        public List<User> retrieveAllUsers();
        public User retrieveUserById(Integer id);
        public User retrieveUserByFullName(String firstName,String lastName);

        public User updateUser(User user);
        public void assignRoleToUser(Integer idRole,Integer idUser);
        public List<User> retrieveAllUsersByRoleName(String roleName);
        public void deleteUser(Integer id);
}
