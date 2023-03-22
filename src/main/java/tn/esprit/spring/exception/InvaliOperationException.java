package tn.esprit.spring.exception;

import lombok.Getter;

public class InvaliOperationException extends RuntimeException{
    @Getter
    private ErrorCodes errorCodes;

    public InvaliOperationException(String message){
        super(message);
    }

    public InvaliOperationException(String message, Throwable cause){
        super(message, cause);
    }

    public InvaliOperationException(String message, Throwable cause, ErrorCodes errorCodes){
        super(message, cause);
        this.errorCodes=errorCodes;
    }

    public InvaliOperationException(String message, ErrorCodes errorCodes){
        super(message);
        this.errorCodes=errorCodes;
    }
}
