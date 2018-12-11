package org.movie.service.comment.model;

import lombok.Data;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */
@Data
public class CommentDetails {

    private String movieId;

    private String userName;

    private String message;
}
