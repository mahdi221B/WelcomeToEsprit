package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByFirstNameContainsAndLastNameContains(String firstname, String lastname);
    List<User> findAllByRolesRoleNameContains(String roleName);
    User findUserByIdentifierContains(String identifier);
     Optional<User> findUserByEmailAddressContains(String email);
}