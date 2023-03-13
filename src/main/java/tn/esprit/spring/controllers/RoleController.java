package tn.esprit.spring.controllers;

import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.controllers.api.RoleApi;
import tn.esprit.spring.dto.RoleDto;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.services.RoleService;

import java.util.List;

@RestController
public class RoleController implements RoleApi {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @Override
    public Role addRole(Role role) {

        return roleService.addRole(role);
    }

    @Override
    public List<Role> retrieveAllRoles() {

        return roleService.retrieveAllRoles();
    }

    @Override
    public Role retrieveRoleByRoleName(String roleName) {
        return roleService.retrieveRoleByRoleName(roleName);
    }

    @Override
    public List<Role> retrieveAllRolesByUserFullName(String firstname, String lastname) {

        return roleService.retrieveAllRolesByUserFullName(firstname, lastname);
    }

    @Override
    public Role updateRole(Role role) {

        return roleService.updateRole(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleService.deleteRole(id);
    }
}
