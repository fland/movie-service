package org.movie.service.movie_detail.dao;

import org.movie.service.movie_detail.model.MovieDetails;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */

public interface MovieRepository {

    MovieDetails getMovieByUuid(String movieUuid);
}
