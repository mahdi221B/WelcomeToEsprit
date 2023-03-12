package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
