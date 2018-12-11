package org.movie.service.movie_detail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "requested movie details not found")
public final class MovieNotFoundException extends RuntimeException {
}
