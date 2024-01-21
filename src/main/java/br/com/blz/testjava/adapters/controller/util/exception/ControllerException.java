package br.com.blz.testjava.adapters.controller.util.exception;

public class ControllerException extends RuntimeException{

    public BaseError getError() {
        return error;
    }

    BaseError error;
    String errorCode;

    public ControllerException(BaseError error) {
        this.error = error;
    }

    public ControllerException(String message)
    {
        super(message);
    }

    public ControllerException(String errorCode,String errorMessage)
    {
        super(errorMessage);
        this.errorCode=errorCode;
    }

    public ControllerException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public ControllerException(Throwable cause)
    {
        super(cause);
    }

    public ControllerException(String message, Throwable cause, Boolean enableSuppression, Boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

