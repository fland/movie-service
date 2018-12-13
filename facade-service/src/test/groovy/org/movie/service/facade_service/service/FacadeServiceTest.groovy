package org.movie.service.facade_service.service

import org.movie.service.facade_service.exception.InvalidRequestBodyException
import org.movie.service.facade_service.model.CommentDetails
import org.movie.service.facade_service.model.MovieDetails
import org.springframework.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

import javax.servlet.http.HttpServletRequest

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */

class FacadeServiceTest extends Specification {

    @Unroll
    def "should throw InvalidRequestBodyException on #movieId movie id"() {
        given:
        def movieDetailsService = Stub(MovieDetailService)
        def commentsService = Stub(CommentsService)
        def facadeService = new FacadeService(movieDetailsService, commentsService)
        def request = Stub(HttpServletRequest)

        when:
        facadeService.postMovieDetails(new MovieDetails(id: movieId, title: 'title', description: 'desc'),
                request)

        then:
        thrown InvalidRequestBodyException

        where:
        movieId << ['', null]
    }

    def "should return created status for POST movie details"() {
        given:
        def movieDetailsService = Stub(MovieDetailService)
        def commentsService = Stub(CommentsService)
        def facadeService = new FacadeService(movieDetailsService, commentsService)
        def request = Mock(HttpServletRequest)
        def movieId = '2'
        def location = 'http://localhost:8080/somePath'

        when:
        def response = facadeService.postMovieDetails(new MovieDetails(id: movieId, title: 'title', description: 'desc'),
                request)

        then:
        1 * request.getRequestURL() >> new StringBuffer(location)
        response.getStatusCode() == HttpStatus.CREATED
        response.getHeaders().containsKey('Location')
        response.getHeaders().getLocation().toString() == "$location/$movieId"
    }

    def "should return movie details without comments"() {
        given:
        def movieDetailsService = Mock(MovieDetailService)
        def commentsService = Mock(CommentsService)
        def facadeService = new FacadeService(movieDetailsService, commentsService)
        def movieId = '1'

        when:
        def actualMovieDetails = facadeService.getMovieDetails(movieId)

        then:
        1 * movieDetailsService.getMovieDetails(movieId) >> new MovieDetails(id: movieId, title: 'title1', description: 'desc1')
        1 * commentsService.getComments(movieId) >> []
        actualMovieDetails.id == movieId
        actualMovieDetails.title == 'title1'
        actualMovieDetails.description == 'desc1'
    }

    def "should return movie details with comments"() {
        given:
        def movieDetailsService = Mock(MovieDetailService)
        def commentsService = Mock(CommentsService)
        def facadeService = new FacadeService(movieDetailsService, commentsService)
        def movieId = '1'
        def expectedComments = [new CommentDetails(userName: 'user1', message: 'msg2'),
                                new CommentDetails(userName: 'user2', message: 'msg3')]

        when:
        def actualMovieDetails = facadeService.getMovieDetails(movieId)

        then:
        1 * movieDetailsService.getMovieDetails(movieId) >> new MovieDetails(id: movieId, title: 'title1', description: 'desc1')
        1 * commentsService.getComments(movieId) >> expectedComments
        actualMovieDetails.id == movieId
        actualMovieDetails.title == 'title1'
        actualMovieDetails.description == 'desc1'
        actualMovieDetails.comments.size() == 2
        actualMovieDetails.comments.containsAll(expectedComments)
    }
}
