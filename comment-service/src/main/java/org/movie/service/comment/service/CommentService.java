package org.movie.service.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movie.service.comment.dao.CommentsRepository;
import org.movie.service.comment.model.CommentDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentsRepository commentsRepository;

    @RequestMapping(value = "/comments/{movieUuid}", produces = APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.GET)
    public List<CommentDetails> getComments(@PathVariable String movieUuid) {
        log.info("Requested comments for movie uuid: {}", movieUuid);
        List<CommentDetails> comments = commentsRepository.getCommentsByMovieUuid(movieUuid);
        log.info("Retrieved comments: {}", comments);
        return comments;
    }
}
