package org.movie.service.movie_detail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movie.service.movie_detail.dao.MovieRepository;
import org.movie.service.movie_detail.exception.MovieNotFoundException;
import org.movie.service.movie_detail.model.MovieDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Maksym Bondarenko
 * @version 1.0 10.12.18
 */

@RestController
@Slf4j
@RequiredArgsConstructor
class MovieService {

    private final MovieRepository movieRepository;

    @RequestMapping(value = "/movie/{uuid}", produces = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    public MovieDetails getMovieDetails(@PathVariable String uuid) {
        log.info("Requested movie uuid: {}", uuid);
        MovieDetails movieDetails = movieRepository.getMovieByUuid(uuid);
        log.info("Retrieved movie details: {}", movieDetails);
        if (movieDetails == null) {
            throw new MovieNotFoundException();
        }
        return movieDetails;
    }
}
