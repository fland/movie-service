package org.movie.service.movie_detail.dao

import org.movie.service.movie_detail.model.MovieDetails
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */

class MovieStaticRepositoryTest extends Specification {

    def "should throw IllegalArgumentException on null movie uuid"() {
        when:
        new MovieStaticRepository().getMovieByUuid(null)

        then:
        thrown IllegalArgumentException
    }

    @Unroll
    def "should return expected movie details for movie #movieUuid"() {
        given:
        def movieRepo = new MovieStaticRepository()

        when:
        def actualMovieDetails = movieRepo.getMovieByUuid(movieUuid)

        then:
        actualMovieDetails == movieDetails

        where:
        movieUuid | movieDetails
        '1'       | new MovieDetails(id: '1', title: 'First movie', description: 'First static movie')
        '2'       | new MovieDetails(id: '2', title: 'Local movie', description: 'Some local movie description')
    }
}
