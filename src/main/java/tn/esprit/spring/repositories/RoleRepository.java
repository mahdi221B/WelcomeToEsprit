package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByUsersFirstNameContainsAndUsersLastNameContains(String firstname, String lastname);
    Optional<Role> findRoleByRoleNameContains(String roleName);

    List<Role> findRolesByUsers(User user);
}