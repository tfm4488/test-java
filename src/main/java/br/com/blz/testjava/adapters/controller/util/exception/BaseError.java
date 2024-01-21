package br.com.blz.testjava.adapters.controller.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import br.com.blz.testjava.adapters.controller.util.Error;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BaseError {
    HttpStatus status;
    Integer code;
    List<Error> messages = new ArrayList<>();

    public BaseError() {
    }

    public BaseError(HttpStatus status, String message) {
        this.status = status;
        this.code = status.value();
        messages.add(Error.builder().cause(message).build());
    }

    public BaseError(HttpStatus status, List<Error> messages) {
        this.status = status;
        this.code = status.value();
        this.messages.addAll(messages);
    }

    public BaseError(String message) {
        messages.add(Error.builder().cause(message).build());
    }
}
