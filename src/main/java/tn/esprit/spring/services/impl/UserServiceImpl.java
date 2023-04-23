package tn.esprit.spring.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tn.esprit.spring.entity.Address;
import tn.esprit.spring.entity.ChangePasswordEntity;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.exception.EntityExistException;
import tn.esprit.spring.exception.EntityNotFoundException;
import tn.esprit.spring.exception.ErrorCodes;
import tn.esprit.spring.exception.InvalidEntityException;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.RoleService;
import tn.esprit.spring.services.UserService;
import tn.esprit.spring.services.VerificationTokenService;
import tn.esprit.spring.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RoleService service;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenService verificationTokenService;
    private EmailService emailService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,
                           RoleService service,PasswordEncoder passwordEncoder,
                           VerificationTokenService verificationTokenService,
                           EmailService emailService
    )
    {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
        this.service=service;
        this.passwordEncoder=passwordEncoder;
        this.verificationTokenService=verificationTokenService;
        this.emailService=emailService;
    }
    @Override
    public User addUser(User user) throws javax.mail.MessagingException {
        List<String> errors= UserValidator.validate(user);
        if (errors.isEmpty()){
            log.error("User is not valid {}",user);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID,errors);
        }
        if (checkEmail(user.getEmailAddress())){
            log.warn("user is already exist");
            throw new EntityExistException("user is already exist",ErrorCodes.OPERATION_INVALID);
        }
        User user1=new User();
        user1.setActive(false);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setNci(user.getNci());
        user1.setEmailAddress(user.getEmailAddress());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setBirthDate(user.getBirthDate());
        user1.setAddress(user.getAddress());
        user1.setRoles(user.getRoles());
        if (user1.getRoles()==null){
            List<Role> roles=new ArrayList<>();
            roles.add(Role.builder()
                    .roleName("Guest"+ user.getNci())
                    .build());
            user1.setRoles(roles);
        }
        User user2=userRepository.save(user1);
        Optional<User> saved=Optional.of(user2) ;
        //create and save verification token is saved
        saved.ifPresent(u->{
            try {
                String token= UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(),token);
                //send verification email
                emailService.sendHtmlMail(u);

            }catch (Exception e){
                e.printStackTrace();
            }
        });
        return saved.get();
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User retrieveUserById(Integer id) {
        if (id==null){
            log.warn("");
            return null;
        }
        return userRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("No Role with this ID"+id+"was found in DB",
                        ErrorCodes.USER_NOT_FOUND
                )
        );
    }
    @Override
    public User retrieveUserByFullName(String firstName, String lastName) {

        if (!StringUtils.hasLength(firstName)|| !StringUtils.hasLength(lastName)){
            log.warn("");
            return null;
        }
        return userRepository.findByFirstNameContainsAndLastNameContains(firstName, lastName).orElseThrow(
                ()->new EntityNotFoundException(
                        "No Role with this firstname"+firstName+"and lastname"+lastName+"was found in DB",
                        ErrorCodes.USER_NOT_FOUND
                )
        );
    }

    @Override
    public User updateUser(User user) {
        List<String> errors= UserValidator.validate(user);
        if (errors.isEmpty()){
            log.error("User is not valid {}",user);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID,errors);
        }
        User user1=userRepository.findById(user.getId()).orElseThrow(
                ()->new EntityNotFoundException("No user with ID="+user.getId()+"was found in DB",
                        ErrorCodes.USER_NOT_FOUND
                )
        );
        if (user.getFirstName()!=null){
            user1.setFirstName(user.getFirstName());
        }
        if (user.getLastName()!=null){
            user1.setLastName(user.getLastName());
        }
        if (user.getNci()!=null){
            user1.setNci(user.getNci());
        }
        if (user.getEmailAddress()!=null){
            user1.setEmailAddress(user.getEmailAddress());
        }
        if (user.getPassword()!=null){
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getBirthDate()!=null){
            user1.setBirthDate(user.getBirthDate());
        }
        if (user.getAddress()!=null){
            Address address=new Address();
            if (user.getAddress().getAddress1()!=null){
                String ad1=user.getAddress().getAddress1();
                address.setAddress1(ad1);
            }
            if (user.getAddress().getAddress2()!=null){
                String ad2=user.getAddress().getAddress2();
                address.setAddress2(ad2);
            }
            if (user.getAddress().getCity()!=null){
                String city=user.getAddress().getCity();
                address.setCity(city);
            }
            if (user.getAddress().getCodePostal()!=null){
                String codeP=user.getAddress().getCodePostal();
                address.setCodePostal(codeP);
            }
            if (user.getAddress().getCountry()!=null){
                String country=user.getAddress().getCountry();
                address.setCountry(country);
            }

            user1.setAddress(address);
        }

        return userRepository.save(user1);
    }

    @Override
    public void deleteUser(Integer id) {
        if (id==null){
            log.warn("");
            return;
        }
        userRepository.deleteById(id);

    }

    @Override
    public User changePassword(ChangePasswordEntity chgPassword) {
        validatePassword(chgPassword);
        User user=userRepository.findById(chgPassword.getId()).orElseThrow(()->
                new EntityNotFoundException("NoOne User with Id was found in DB",
                        ErrorCodes.USER_NOT_FOUND
                )
        );
        /**  String currentPasswordEncode= passwordEncoder.encode(chgPassword.getCurrentPassword());
         if (!user.getPassword().equals(currentPasswordEncode)){
         log.warn("please verify your current password");
         throw new InvalidEntityException("your current password is invalid",
         ErrorCodes.USER_PASSWORD_MODIFY_FAILED
         );
         }*/
        User user1=user;
        user1.setPassword(passwordEncoder.encode(chgPassword.getNewPassword()));
        return userRepository.save(user1);
    }

    private void validatePassword(ChangePasswordEntity changePassword){
        if (changePassword.getId()==null){
            log.warn("please give the ID of user for change the Password");
            throw new InvalidEntityException("Please fill an ID for Modify Password",
                    ErrorCodes.USER_PASSWORD_MODIFY_FAILED
            );
        }
        if (!StringUtils.hasLength(changePassword.getNewPassword())||
                !StringUtils.hasLength(changePassword.getConfirmNewPassword())
        ){
            log.warn("please give the current and new password of user for change the Password");
            throw new InvalidEntityException("Please fill the current and new Password for Modify Password",
                    ErrorCodes.USER_PASSWORD_MODIFY_FAILED
            );
        }
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())){
            log.warn("please give the same Password for field newPassword and confirmNewPassword");
            throw new InvalidEntityException("Please fill the same Password for field newPassword and confirmNewPassword",
                    ErrorCodes.USER_PASSWORD_MODIFY_FAILED
            );
        }
    }

    private boolean checkEmail(String email) {
        Optional<User> user=userRepository.findUserByEmailAddressContains(email);
        if (user.isPresent()){
            return true;
        }

        return false;
    }

    @Override
    public List<User> retrieveAllUsersByRoleName(String roleName) {
        if (!StringUtils.hasLength(roleName)){
            log.warn("");
            return null;
        }
        return userRepository.findAllByRolesRoleNameContains(roleName);
    }

    @Override
    public void assignRoleToUser(Integer idRole, Integer idUser) {
        if (idRole == null || idUser == null) {
            return;
        }
        Role role = roleRepository.findById(idRole).orElseThrow(() ->
                new EntityNotFoundException("No Role with this" + idRole + "was found in DB",
                        ErrorCodes.ROLE_NOT_FOUND
                )
        );

        User user = userRepository.findById(idUser).orElseThrow(() ->
                new EntityNotFoundException("No Role with this" + idUser + "was found in DB",
                        ErrorCodes.USER_NOT_FOUND
                )
        );
        if (user.getRoles().isEmpty()) {
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
        } else {
            if (checkListRole(user,role)){
                throw new EntityExistException("User has already this role",ErrorCodes.OPERATION_INVALID);
            }
            user.getRoles().add(role);
        }
        userRepository.save(user);
    }
    private boolean checkListRole(User user,Role role){
        List<Role> roles=roleRepository.findRolesByUsers(user);
        List<Role> roles1=new ArrayList<>();
        roles.forEach(r->{
            Role role1=roleRepository.findRoleByRoleNameContains(r.getRoleName()).orElseThrow(
                    ()->new EntityNotFoundException("role is not found",ErrorCodes.ROLE_NOT_FOUND)
            );
            if (role1==role){
                roles1.add(role1);
            }
        });
        if (!roles1.isEmpty()){
            return true;
        }
        return false;
    }
}
