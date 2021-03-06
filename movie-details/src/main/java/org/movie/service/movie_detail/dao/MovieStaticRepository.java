package org.movie.service.movie_detail.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.movie.service.movie_detail.model.MovieDetails;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */

@Repository
class MovieStaticRepository implements MovieRepository {

    private final Map<String, MovieDetails> movies;

    public MovieStaticRepository() throws IOException {
        movies = new ObjectMapper().readValue(new ClassPathResource("movies.json").getInputStream(),
                new TypeReference<Map<String, MovieDetails>>() {
                });
    }

    @Override
    public MovieDetails getMovieById(@NonNull String movieId) {
        return movies.get(movieId);
    }
}
