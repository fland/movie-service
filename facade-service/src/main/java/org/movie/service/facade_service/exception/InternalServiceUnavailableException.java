package org.movie.service.facade_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */
@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "internal service isn't available")
public final class InternalServiceUnavailableException extends RuntimeException {
}
