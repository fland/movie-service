package org.movie.service.comment.dao;

import org.movie.service.comment.model.CommentDetails;

import java.util.List;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */

public interface CommentsRepository {

    List<CommentDetails> getCommentsByMovieUuid(String movieUuid);
}
