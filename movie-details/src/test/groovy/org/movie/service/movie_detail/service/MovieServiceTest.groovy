package org.movie.service.movie_detail.service

import org.movie.service.movie_detail.dao.MovieRepository
import org.movie.service.movie_detail.exception.MovieNotFoundException
import org.movie.service.movie_detail.model.MovieDetails
import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */

class MovieServiceTest extends Specification {

    def "should return movie details"() {
        given:
        def movieRepo = Mock(MovieRepository)
        def movieService = new MovieService(movieRepo)
        def movieId = '1'
        def movieTitle = 'title'
        def movieDesc = 'desc'

        when:
        def movieDetails = movieService.getMovieDetails('1')

        then:
        1 * movieRepo.getMovieByUuid('1') >> new MovieDetails(id: movieId, title: movieTitle, description: movieDesc)
        movieDetails.id == movieId
        movieDetails.title == movieTitle
        movieDetails.description == movieDesc
    }

    def "should throw MovieNotFoundException when movie wasn't found"() {
        given:
        def movieRepo = Mock(MovieRepository)
        def movieService = new MovieService(movieRepo)

        when:
        movieService.getMovieDetails('1')

        then:
        1 * movieRepo.getMovieByUuid('1') >> null
        thrown MovieNotFoundException
    }
}
