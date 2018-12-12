package org.movie.service.facade_service.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movie.service.facade_service.exception.InternalServiceUnavailableException;
import org.movie.service.facade_service.exception.MovieNotFoundException;
import org.movie.service.facade_service.model.MovieDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */
@Slf4j
@Component
@RequiredArgsConstructor
class MovieDetailService {

    private final RestOperations restTemplate;

    @Value("${movieDetails.movie}")
    String movieDetailsMoviePath;

    MovieDetails getMovieDetails(@NonNull String movieId) {
        MovieDetails movieDetails;
        try {
            movieDetails = restTemplate.getForObject(movieDetailsMoviePath + movieId, MovieDetails.class);
        } catch (HttpClientErrorException.NotFound e) {
            log.error("Retrieved HTTP 404 from movie details service: " + e, e);
            throw new MovieNotFoundException();
        } catch (ResourceAccessException e) {
            log.error("Movie details service is unavailable: " + e, e);
            throw new InternalServiceUnavailableException();
        }
        log.info("Movie details: {}", movieDetails);
        return movieDetails;
    }
}
