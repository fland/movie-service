package org.movie.service.comment.service

import org.movie.service.comment.dao.CommentsRepository
import org.movie.service.comment.model.CommentDetails
import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */

class CommentServiceTest extends Specification {

    def "should return comments"() {
        given:
        def commentsRepo = Mock(CommentsRepository)
        def commentService = new CommentService(commentsRepo)
        def movieId = '1'
        def expectedComments = [new CommentDetails(movieId: '1', userName: 'user1', message: 'msg1'),
                                new CommentDetails(movieId: '1', userName: 'user2', message: 'msg2'),
                                new CommentDetails(movieId: '1', userName: 'user1', message: 'msg3')]

        when:
        def actualComments = commentService.getComments(movieId)

        then:
        1 * commentsRepo.getCommentsByMovieUuid(movieId) >> expectedComments
        actualComments.containsAll(expectedComments)
        expectedComments.containsAll(actualComments)
    }

    def "should return empty list when no comments for a movie"() {
        given:
        def commentsRepo = Mock(CommentsRepository)
        def commentService = new CommentService(commentsRepo)
        def movieId = '1'

        when:
        def actualComments = commentService.getComments(movieId)

        then:
        1 * commentsRepo.getCommentsByMovieUuid(movieId) >> []
        actualComments.isEmpty()
    }
}
