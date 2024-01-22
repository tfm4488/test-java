package br.com.blz.testjava.adapters.controller.util.exception;

public class NotFoundException extends RuntimeException{

    public BaseError getError() {
        return error;
    }

    BaseError error;
    String errorCode;

    public NotFoundException(BaseError error) {
        this.error = error;
    }

    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException(String errorCode, String errorMessage)
    {
        super(errorMessage);
        this.errorCode=errorCode;
    }

    public NotFoundException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public NotFoundException(Throwable cause)
    {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, Boolean enableSuppression, Boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

