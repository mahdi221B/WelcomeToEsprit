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
    @Query("select u from User u join u.roles role where role.roleName = 'ROLE_TEACHER' ")
    List<User> findTeachersdisponibilty();
}