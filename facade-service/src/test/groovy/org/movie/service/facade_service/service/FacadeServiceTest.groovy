package org.movie.service.facade_service.service

import org.movie.service.facade_service.model.CommentDetails
import org.movie.service.facade_service.model.MovieDetails
import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */

class FacadeServiceTest extends Specification {

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
