package org.movie.service.facade_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movie.service.facade_service.model.CommentDetails;
import org.movie.service.facade_service.model.MovieDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */
@RestController
@Slf4j
@RequiredArgsConstructor
class FacadeService {

    private final MovieDetailService movieDetailService;
    private final CommentsService commentsService;

    @RequestMapping(value = "/movie/{movieId}", produces = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    public MovieDetails getMovieDetails(@PathVariable String movieId) {
        log.info("Requested movie id: {}", movieId);

        MovieDetails movieDetails = movieDetailService.getMovieDetails(movieId);
        List<CommentDetails> comments = commentsService.getComments(movieId);
        movieDetails.setComments(comments);

        return movieDetails;
    }
}
