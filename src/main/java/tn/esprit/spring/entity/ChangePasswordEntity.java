package tn.esprit.spring.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordEntity {
    Integer id;
    String currentPassword;
    String newPassword;
    String confirmNewPassword;
}
