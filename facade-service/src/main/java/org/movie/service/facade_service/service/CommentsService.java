package org.movie.service.facade_service.service;

import org.movie.service.facade_service.model.CommentDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */
@Component
class CommentsService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${commentService.comments}")
    private String commentsServiceCommentsPath;

    List<CommentDetails> getComments(String movieId) {
        return restTemplate.exchange(commentsServiceCommentsPath + movieId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentDetails>>() {
                }).getBody();
    }
}
