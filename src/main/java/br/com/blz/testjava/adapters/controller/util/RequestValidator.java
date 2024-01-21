package br.com.blz.testjava.adapters.controller.util;

import br.com.blz.testjava.adapters.controller.util.exception.BaseError;
import br.com.blz.testjava.adapters.controller.util.exception.ControllerException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RequestValidator {
    public <T> void validate(T requestBody){

        List<Error> errorMessages = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(requestBody);

        for (ConstraintViolation<T> violation : violations) {
            errorMessages.add(Error.builder()
                .field(String.valueOf(violation.getPropertyPath()))
                .cause(violation.getMessage())
                .value(String.valueOf(violation.getInvalidValue()))
                .build());
        }

        if(!errorMessages.isEmpty()){
            throw new ControllerException(new BaseError(HttpStatus.BAD_REQUEST,errorMessages));
        }
    }
}
