package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByUsersFirstNameContainsAndUsersLastNameContains(String firstname, String lastname);
    Role findRoleByRoleNameContains(String roleName);
}