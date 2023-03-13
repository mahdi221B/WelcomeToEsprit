package tn.esprit.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.entity.AbstractEntity;
import tn.esprit.spring.entity.Address;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto extends AbstractEntity {
    String lastName;
    String firstName;
    String emailAddress;
    String identifier;
    String nci;
    Date birthDate;
    String password;
    @JsonIgnore
    Address address;
    String picture;
    @JsonIgnore
    List<Role> roles;

    public static UserDto fromEntity(User user){
        if (user==null){
            return null;
        }

        return UserDto.builder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .emailAddress(user.getEmailAddress())
                .nci(user.getNci())
                .birthDate(user.getBirthDate())
                .password(user.getPassword())
                .picture(user.getPicture())
                .build();
    }

    public static User toEntity(UserDto userDto){
        if (userDto==null){
            return null;
        }

        User user=new User();
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setNci(userDto.getNci());
        user.setBirthDate(userDto.getBirthDate());
        user.setPassword(userDto.getPassword());
        user.setPicture(userDto.getPicture());

        return user;
    }
}
