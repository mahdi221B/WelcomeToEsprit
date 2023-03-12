package tn.esprit.spring.services;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

import java.util.List;

public interface IServiceUserManager {
    public User addUser(User user);
    public List<User> retrieveAllUsers();
    public User retrieveUserById(Integer id);
    public User retrieveUserByFullName(String firstName,String lastName);
    public User updateUser(User user);
    public void deleteUser(Integer id);
    public void assignRoleToUser(Integer idRole,Integer idUser);
    public List<User> retrieveAllUsersByRoleName(String roleName);

    public Role addRole(Role role);
    public List<Role> retrieveAllRoles();
    public Role retrieveRoleByRoleName(String roleName);
    public List<Role> retrieveAllRolesByUserFullName(String firstname,String lastname);
    public Role updateRole(Role role);
    public void deleteRole(Integer id);

}
