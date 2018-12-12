package org.movie.service.facade_service.service

import org.movie.service.facade_service.exception.InternalServiceUnavailableException
import org.movie.service.facade_service.exception.MovieNotFoundException
import org.movie.service.facade_service.model.MovieDetails
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestOperations
import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */

class MovieDetailServiceTest extends Specification {

    def "should throw IllegalArgumentException on null movie id"() {
        when:
        new MovieDetailService(Mock(RestOperations)).getMovieDetails(null)

        then:
        thrown IllegalArgumentException
    }

    def "should return movie details"() {
        given:
        def restTemplate = Mock(RestOperations)
        def movieDetailService = new MovieDetailService(restTemplate)
        def movieId = '1'

        when:
        def actualMovieDetails = movieDetailService.getMovieDetails(movieId)

        then:
        1 * restTemplate.getForObject(movieDetailService.movieDetailsMoviePath + movieId,
                MovieDetails.class) >> new MovieDetails(id: movieId, title: 'movie1', description: 'desc1')
        actualMovieDetails.id == movieId
        actualMovieDetails.title == 'movie1'
        actualMovieDetails.description == 'desc1'
    }

    def "should throw MovieNotFoundException when movie wasn't found"() {
        given:
        def restTemplate = Mock(RestOperations)
        def movieDetailService = new MovieDetailService(restTemplate)
        def movieId = '1'

        when:
        movieDetailService.getMovieDetails(movieId)

        then:
        1 * restTemplate.getForObject(movieDetailService.movieDetailsMoviePath + movieId,
                MovieDetails.class) >> {
            throw HttpClientErrorException.create(HttpStatus.NOT_FOUND, null, null, null, null)
        }
        thrown MovieNotFoundException
    }

    def "should throw InternalServiceUnavailableException when movie details service unavailable"() {
        given:
        def restTemplate = Mock(RestOperations)
        def movieDetailService = new MovieDetailService(restTemplate)
        def movieId = '1'

        when:
        movieDetailService.getMovieDetails(movieId)

        then:
        1 * restTemplate.getForObject(movieDetailService.movieDetailsMoviePath + movieId,
                MovieDetails.class) >> { throw new ResourceAccessException('smth went wrong!') }
        thrown InternalServiceUnavailableException
    }
}
