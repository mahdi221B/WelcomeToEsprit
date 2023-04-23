package tn.esprit.spring.services;

import tn.esprit.spring.entity.ChangePasswordEntity;
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

    public User changePassword(ChangePasswordEntity chgPassword);
    // public boolean checkEmail(String email);
}
