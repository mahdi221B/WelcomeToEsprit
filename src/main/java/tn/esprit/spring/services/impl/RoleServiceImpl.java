package tn.esprit.spring.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tn.esprit.spring.dto.RoleDto;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.exception.EntityNotFoundException;
import tn.esprit.spring.exception.ErrorCodes;
import tn.esprit.spring.exception.InvalidEntityException;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.services.RoleService;
import tn.esprit.spring.validator.RoleValidator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addRole(Role role) {
        List<String> errors= RoleValidator.validate(role);
        if (!errors.isEmpty()){
            log.error("Role is not valid {}",role);
            throw new InvalidEntityException("Role is not valid", ErrorCodes.ROLE_NOT_VALID,errors);
        }
        return roleRepository.save(role);
    }

    @Override
    public List<Role> retrieveAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role retrieveRoleByRoleName(String roleName) {
        if (!StringUtils.hasLength(roleName)){
            log.warn("RoleName is null");
            return null;
        }

        return roleRepository.findRoleByRoleNameContains(roleName).orElseThrow(()->
                new EntityNotFoundException(
                        "No Role with this name"+roleName+" was found in the DB",
                        ErrorCodes.ROLE_NOT_FOUND
                )
        );
    }

    @Override
    public List<Role> retrieveAllRolesByUserFullName(String firstname, String lastname) {
        if (!StringUtils.hasLength(firstname)|| !StringUtils.hasLength(lastname)){
            log.warn("firstname or lastname is not valid");
            return null;
        }
        return roleRepository.findAllByUsersFirstNameContainsAndUsersLastNameContains(firstname, lastname);
    }

    @Override
    public Role updateRole(Role role) {
        List<String> errors= RoleValidator.validate(role);
        if (!errors.isEmpty()){
            log.error("Role is not valid {}",role);
            throw new InvalidEntityException("Role is not valid", ErrorCodes.ROLE_NOT_VALID,errors);
        }
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        if (id==null){
            log.warn("Role ID is not valid");
            return;
        }

        roleRepository.deleteById(id);
    }
}
