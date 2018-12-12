package org.movie.service.comment.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.movie.service.comment.model.CommentDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */
@Component
class CommentsStaticRepository implements CommentsRepository {

    private final Map<String, List<CommentDetails>> comments;

    public CommentsStaticRepository() throws IOException {
        comments = new ObjectMapper().readValue(ResourceUtils.getFile("classpath:comments.json"), new TypeReference<Map<String, List<CommentDetails>>>() {
        });
    }

    @Override
    public List<CommentDetails> getCommentsByMovieId(@NonNull String movieId) {
        return comments.getOrDefault(movieId, Collections.emptyList());
    }
}
