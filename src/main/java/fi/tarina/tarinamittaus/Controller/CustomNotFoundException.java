package fi.tarina.tarinamittaus.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException() {
    }

    public CustomNotFoundException(String message) {
        super(message);
    }
}
