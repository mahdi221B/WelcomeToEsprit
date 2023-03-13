package tn.esprit.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entity.AbstractEntity;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto extends AbstractEntity {
    String roleName;
    @JsonIgnore
    List<User> users;

    public static RoleDto fromEntity(Role role){
        if (role==null){
            return null;
        }

        return RoleDto.builder()
                .roleName(role.getRoleName())
                .build();
    }

    public static Role toEntity(RoleDto roleDto){
        if (roleDto==null){
            return null;
        }

        Role role=new Role();
        role.setRoleName(roleDto.getRoleName());

        return role;
    }
}
