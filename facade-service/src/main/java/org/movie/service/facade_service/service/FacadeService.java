package org.movie.service.facade_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movie.service.facade_service.exception.InvalidRequestBodyException;
import org.movie.service.facade_service.model.CommentDetails;
import org.movie.service.facade_service.model.MovieDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
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

    @RequestMapping(value = "/movie", produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity postMovieDetails(@RequestBody MovieDetails movieDetails, HttpServletRequest request) throws URISyntaxException {
        log.info("POST Movie details: {}", movieDetails);
        if (StringUtils.isEmpty(movieDetails.getId())) {
            throw new InvalidRequestBodyException("null or empty movie id isn't allowed");
        }
        return ResponseEntity.created(new URI(request.getRequestURL() + "/" + movieDetails.getId())).build();
    }

    @RequestMapping(value = "/comment", produces = APPLICATION_JSON_UTF8_VALUE,
            consumes = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity postComment(@RequestBody CommentDetails comment) {
        log.info("POST comment: {}", comment);
        if (StringUtils.isEmpty(comment.getMovieId())) {
            throw new InvalidRequestBodyException("null or empty movie id isn't allowed");
        }
        return ResponseEntity.ok().build();
    }
}
