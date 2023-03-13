package tn.esprit.spring.controllers;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.controllers.api.UserApi;
import tn.esprit.spring.dto.UserDto;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.utils.QrcodeGenerated;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController implements UserApi {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User addUser(User user) throws  javax.mail.MessagingException {
        return userService.addUser(user);
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }

    @Override
    public User retrieveUserById(Integer id) {
        return userService.retrieveUserById(id);
    }

    @Override
    public User retrieveUserByFullName(String firstName, String lastName) {
        return userService.retrieveUserByFullName(firstName, lastName);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userService.deleteUser(id);
    }

    @Override
    public void assignRoleToUser(Integer idRole, Integer idUser) {
        userService.assignRoleToUser(idRole,idUser);
    }
    @Override
    public List<User> retrieveAllUsersByRoleName(String roleName) {
        return userService.retrieveAllUsersByRoleName(roleName);
    }

}
