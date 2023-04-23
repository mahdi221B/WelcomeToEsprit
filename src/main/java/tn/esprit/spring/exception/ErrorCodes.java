package tn.esprit.spring.exception;

public enum ErrorCodes {
      USER_NOT_FOUND(1000),
      USER_NOT_VALID(1001),
      ROLE_NOT_FOUND(2000),
      ROLE_NOT_VALID(2001),
      ROLE_EXIST_ALREADY(3000),
    OPERATION_INVALID(4000), USER_PASSWORD_MODIFY_FAILED(5000);

    private int code;

     ErrorCodes(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }
}
