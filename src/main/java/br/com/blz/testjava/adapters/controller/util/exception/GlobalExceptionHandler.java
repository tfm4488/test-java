package br.com.blz.testjava.adapters.controller.util.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<BaseError> handleControllerException(ControllerException e){
        return new ResponseEntity(e.getError(), e.getError().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseError> handleGenericException(Exception e){
        BaseError error = new BaseError(HttpStatus.INTERNAL_SERVER_ERROR,"Controller exception: " + e.getMessage());

        return new ResponseEntity(error, error.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseError> handleGenericException(NotFoundException e){
        BaseError error = new BaseError(HttpStatus.NOT_FOUND,"Controller exception: " + e.getMessage());

        return new ResponseEntity(error,error.getStatus());
    }


}
