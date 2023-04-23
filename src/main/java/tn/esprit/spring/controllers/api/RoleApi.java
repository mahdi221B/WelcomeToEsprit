package tn.esprit.spring.controllers.api;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Role;

import java.util.List;

import static tn.esprit.spring.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/roles")
public interface RoleApi {
    @PostMapping(value =APP_ROOT + "/roles/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Role addRole(@RequestBody Role role);
    @GetMapping(value = APP_ROOT + "/roles/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> retrieveAllRoles();
    @GetMapping(value = APP_ROOT + "/roles/{roleName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Role retrieveRoleByRoleName(@PathVariable("roleName") String roleName);
    @GetMapping(value =APP_ROOT + "/roles/byUser/{firstname}/{lastname}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> retrieveAllRolesByUserFullName(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname);
    @PutMapping(value =APP_ROOT + "/roles/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Role updateRole(@RequestBody Role role);
    @DeleteMapping(value =APP_ROOT + "/roles/delete/{idRole}")
    public void deleteRole(@PathVariable("idRole") Integer id);
}
