package org.movie.service.facade_service.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movie.service.facade_service.exception.InternalServiceUnavailableException;
import org.movie.service.facade_service.model.CommentDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;

import java.util.List;

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */
@Service
@Slf4j
@RequiredArgsConstructor
class CommentsService {

    private final RestOperations restTemplate;

    @Value("${commentService.comments}")
    String commentsServiceCommentsPath;

    List<CommentDetails> getComments(@NonNull String movieId) {
        try {
            return restTemplate.exchange(commentsServiceCommentsPath + movieId,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentDetails>>() {
                    }).getBody();
        } catch (ResourceAccessException e) {
            log.error("Comments service is unavailable: " + e, e);
            throw new InternalServiceUnavailableException();
        }
    }
}
