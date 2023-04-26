package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceUserManager implements IServiceUserManager {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /****************************CRUD-USER*************************************************/
    /**
     * @param user
     */
    @Override
    public User addUser(User user) {
      return userRepository.save(user);

    }

    /**
     * @return
     */
    @Override
    public List<User> retrieveAllUsers() {
        return(List<User>) userRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User retrieveUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * @param firstName
     * @param lastName
     * @return
     */
    @Override
    public User retrieveUserByFullName(String firstName, String lastName) {
        User user=userRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName);
        return user;
    }
    /**
     * @param roleName
     * @return
     */
    @Override
    public List<User> retrieveAllUsersByRoleName(String roleName) {
        List<User> users= userRepository.findAllByRolesRoleNameContains(roleName);
        return users;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * @param idRole
     * @param idUser
     * @return
     */
    @Override
    public void assignRoleToUser(Integer idRole, Integer idUser) {
        Role role=roleRepository.findById(idRole).orElse(null);
        User user=userRepository.findById(idUser).orElse(null);
        if(user.getRoles()==null){
            List<Role> roles=new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
        }else {
            user.getRoles().add(role);
        }
        userRepository.save(user);
    }

    /****************************CRUD-ROLE*************************************************/
    /**
     * @param role
     * @return
     */
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * @return
     */
    @Override
    public List<Role> retrieveAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * @param roleName
     * @return
     */
    @Override
    public Role retrieveRoleByRoleName(String roleName) {
        Role role=roleRepository.findRoleByRoleNameContains(roleName);
        return role;
    }

    /**
     * @param firstname
     * @param lastname
     * @return
     */
    @Override
    public List<Role> retrieveAllRolesByUserFullName(String firstname, String lastname) {
        List<Role> roles=roleRepository.findAllByUsersFirstNameContainsAndUsersLastNameContains(firstname, lastname);
        return roles;
    }

    /**
     * @param role
     * @return
     */
    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * @param id
     */
    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override 
    public List<Object[]>  allfindUserRoles(){

        return userRepository.findUserRolesall();
    }

}
