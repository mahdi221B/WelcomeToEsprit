package tn.esprit.spring.controllers.api;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import io.swagger.annotations.Api;
import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.dto.UserDto;
import tn.esprit.spring.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static tn.esprit.spring.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/users")
public interface UserApi {
    @PostMapping(value = APP_ROOT + "/users/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) throws  javax.mail.MessagingException;
    @GetMapping(value =APP_ROOT + "/users/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> retrieveAllUsers();
    @GetMapping(value = APP_ROOT + "/users/{idUser}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User retrieveUserById(@PathVariable("idUser") Integer id);
    @GetMapping(value =APP_ROOT + "/users/{firstname}/{lastname}",produces = MediaType.APPLICATION_JSON_VALUE)
    public User retrieveUserByFullName(@PathVariable("firstname") String firstName,@PathVariable("lastname") String lastName);
    @PutMapping(value =APP_ROOT + "/users/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user);
    @DeleteMapping(value =APP_ROOT + "/users/{idUser}")
    public void deleteUser(@PathVariable("idUser") Integer id);
    @PutMapping(value =APP_ROOT + "/users/assignRole/{idRole}/{idUser}")
    public void assignRoleToUser(@PathVariable("idRole") Integer idRole,@PathVariable("idUser") Integer idUser);
    @GetMapping(value =APP_ROOT + "/users/byRoleName/{roleName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> retrieveAllUsersByRoleName(@PathVariable("roleName") String roleName);

}
