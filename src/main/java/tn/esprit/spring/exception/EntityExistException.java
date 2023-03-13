package tn.esprit.spring.exception;

import lombok.Getter;

public class EntityExistException extends RuntimeException{
    @Getter
    private ErrorCodes errorCodes;

    public EntityExistException(String message){
        super(message);
    }

    public EntityExistException(String message, Throwable cause){
        super(message, cause);
    }

    public EntityExistException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes=errorCodes;
    }

    public EntityExistException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }
}
