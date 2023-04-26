package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByFirstNameContainsAndLastNameContains(String firstname,String lastname);
    List<User> findAllByRolesRoleNameContains(String roleName);
    
        @Query(value="SELECT u.firstName, r.roleName FROM users u JOIN users_roles ur ON u.id = ur.users_id JOIN roles r ON ur.roles_id = r.id", nativeQuery = true)
    List<Object[]> findUserRolesall();


}   