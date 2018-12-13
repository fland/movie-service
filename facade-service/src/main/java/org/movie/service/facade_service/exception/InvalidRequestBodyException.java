package org.movie.service.facade_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksym Bondarenko
 * @version 1.0 13.12.18
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public final class InvalidRequestBodyException extends RuntimeException {

    public InvalidRequestBodyException(String message) {
        super(message);
    }
}
