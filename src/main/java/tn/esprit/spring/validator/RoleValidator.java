package tn.esprit.spring.validator;

import org.springframework.util.StringUtils;
import tn.esprit.spring.dto.RoleDto;
import tn.esprit.spring.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    public static List<String> validate(Role dto){

        List<String> errors=new ArrayList<>();

        if (dto==null){
            errors.add("Veuillez remplir ces champs de la formulaire");
            errors.add("Veuillez renseigner RoleName");
            return errors;
        }else {
            if (!StringUtils.hasLength(dto.getRoleName())){
                errors.add("Veuillez renseigner RoleName");
            }
        }
        return errors;
    }
}
