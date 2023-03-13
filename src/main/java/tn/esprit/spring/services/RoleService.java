package tn.esprit.spring.services;

import tn.esprit.spring.dto.RoleDto;
import tn.esprit.spring.entity.Role;

import java.util.List;

public interface RoleService {

    public Role addRole(Role role);
    public List<Role> retrieveAllRoles();
    public Role retrieveRoleByRoleName(String roleName);
    public List<Role> retrieveAllRolesByUserFullName(String firstname,String lastname);
    public Role updateRole(Role role);
    public void deleteRole(Integer id);
}
