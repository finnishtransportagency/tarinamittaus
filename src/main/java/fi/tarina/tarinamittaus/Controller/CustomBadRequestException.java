package fi.tarina.tarinamittaus.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException() {
    }

    public CustomBadRequestException(String message) {
        super(message);
    }

}
