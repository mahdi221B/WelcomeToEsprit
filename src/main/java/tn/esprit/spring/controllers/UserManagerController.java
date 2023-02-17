package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.IServiceUserManager;

import javax.persistence.SqlResultSetMapping;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserManagerController {
    private final IServiceUserManager iServiceUserManager;

/********************************************User_Controller***********************************/
    @PostMapping("/add/User")
    @ResponseBody
    public User addUser(@RequestBody User user){
        return iServiceUserManager.addUser(user);
    }
    @PutMapping("/update/User")
    @ResponseBody
    public User updateUser(@RequestBody User user ){
        return iServiceUserManager.updateUser(user);
    }
    @PutMapping("/assign/RoleToUser/{ID_USER}/{ID_ROLE}")
    @ResponseBody
    public void assignRoleToUser(@PathVariable("ID_ROLE")Integer idRole,@PathVariable("ID_USER")Integer idUser){
         iServiceUserManager.assignRoleToUser(idRole, idUser);
    }

    @DeleteMapping("/delete/User/{ID_USER}")
    public void deleteUser(@PathVariable("ID_USER")Integer idUser){
        iServiceUserManager.deleteUser(idUser);
    }
    @GetMapping("/get/UserById/{ID_USER}")
    @ResponseBody
    public User retrieveUserById(@PathVariable("ID_USER")Integer idUser){
        return iServiceUserManager.retrieveUserById(idUser);
    }
    @GetMapping("/get/UserByFullName/{L_NAME}/{F_NAME}")
    @ResponseBody
    public User retrieveUserByFullName(@PathVariable("L_NAME")String lastname,@PathVariable("F_NAME")String firstname ){
        return iServiceUserManager.retrieveUserByFullName(firstname,lastname);
    }
    @GetMapping("/get/UserByRoleName/{R_NAME}")
    @ResponseBody
    public List<User> retrieveAllUsersByRoleName(@PathVariable("R_NAME")String roleName){
        return iServiceUserManager.retrieveAllUsersByRoleName(roleName);
    }
    @GetMapping("/get/all/Users")
    @ResponseBody
    public List<User> retrieveAllUsers(){
        return iServiceUserManager.retrieveAllUsers();
    }
/********************************************User_Controller***********************************/
    @PostMapping("/add/Role")
    @ResponseBody
    public Role addRole(@RequestBody Role role){
        return iServiceUserManager.addRole(role);
    }
    @PutMapping("/update/Role")
    @ResponseBody
    public Role updateRole(@RequestBody Role role){
        return iServiceUserManager.updateRole(role);
    }
    @DeleteMapping("/delete/Role/{ID_ROLE}")
    @ResponseBody
    public void deleteRole(@PathVariable("ID_ROLE")Integer idRole){
        iServiceUserManager.deleteRole(idRole);
    }
    @GetMapping("/get/RoleByRoleName/{R_NAME}")
    @ResponseBody
    public Role retrieveRoleByRoleName(@PathVariable("R_NAME")String roleName){
        return iServiceUserManager.retrieveRoleByRoleName(roleName);
    }
    @GetMapping("/get/RolesByUser/{L_NAME}/{F_NAME}")
    @ResponseBody
    public List<Role> retrieveRolesByUserFullName(@PathVariable("L_NAME")String lastname,@PathVariable("F_NAME")String firstname){
        return iServiceUserManager.retrieveAllRolesByUserFullName(firstname,lastname);
    }
    @GetMapping("/get/all/roles")
    @ResponseBody
    public List<Role> retrieveAllRoles(){
        return iServiceUserManager.retrieveAllRoles();
    }
}
