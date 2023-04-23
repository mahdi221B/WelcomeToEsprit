package tn.esprit.spring.validator;

import org.springframework.util.StringUtils;
import tn.esprit.spring.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(User dto){

        List<String> errors=new ArrayList<>();
        if (dto==null){
            errors.add("Please complete these fields of the form");
            errors.add("Please fill in the lastname");
            errors.add("Please fill in the firstname");
            errors.add("Please fill in the emailAddress");
            errors.add("Please fill in the birthdate");
            errors.add("Please fill in the identifier");
            errors.add("Please fill in the nci");
            errors.add("Please fill in the password");
            errors.add("Please complete these fields of the form");
            errors.add("Please fill in the Address 1");
            errors.add("Please fill in the city");
            errors.add("Please fill in the Postal code");
            errors.add("Please fill in the Country");
            return errors;
        }else {
            if (!StringUtils.hasLength(dto.getLastName())){
                errors.add("Please fill in the lastname");
            }
            if (!StringUtils.hasLength(dto.getFirstName())){
                errors.add("Please fill in the firstname");
            }
            if (!StringUtils.hasLength(dto.getEmailAddress())){
                errors.add("Please fill in the emailAddress");
            }
            if (dto.getBirthDate()==null){
                errors.add("Please fill in the birthdate");
            }
            if (!StringUtils.hasLength(dto.getIdentifier())){
                errors.add("Please fill in the identifier");
            }
            if (!StringUtils.hasLength(dto.getNci())){
                errors.add("Please fill in the nci");
            }
            if (!StringUtils.hasLength(dto.getPassword())){
                errors.add("Please fill in the password");
            }
            if (dto.getAddress()==null){
                errors.add("Please complete these fields of the form");
                errors.add("Please fill in the Address 1");
                errors.add("Please fill in the city");
                errors.add("Please fill in the Postal code");
                errors.add("Please fill in the Country");
            }else {
                if (!StringUtils.hasLength(dto.getAddress().getAddress1())){
                    errors.add("Please fill in the Address 1");
                }
                if (!StringUtils.hasLength(dto.getAddress().getCity())){
                    errors.add("Please fill in the city");
                }
                if (!StringUtils.hasLength(dto.getAddress().getCodePostal())){
                    errors.add("Please fill in the Postal code");
                }
                if (!StringUtils.hasLength(dto.getAddress().getCountry())){
                    errors.add("Please fill in the Country");
                }
            }
        }
        return errors;
    }
}
