package org.movie.service.facade_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maksym Bondarenko
 * @version 1.0 10.12.18
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetails implements Serializable {

    private String id;

    private String title;

    private String description;

    private List<CommentDetails> comments;
}
