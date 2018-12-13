package org.movie.service.facade_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDetails {

    private String movieId;

    private String userName;

    private String message;
}
