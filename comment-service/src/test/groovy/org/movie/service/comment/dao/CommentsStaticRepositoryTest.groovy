package org.movie.service.comment.dao

import org.movie.service.comment.model.CommentDetails
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Maksym Bondarenko
 * @version 1.0 11.12.18
 */

class CommentsStaticRepositoryTest extends Specification {

    def "should throw IllegalArgumentException on null movie id"() {
        when:
        new CommentsStaticRepository().getCommentsByMovieId(null)

        then:
        thrown IllegalArgumentException
    }

    def "should return empty list on no comments for a movie"() {
        when:
        def actualComments = new CommentsStaticRepository().getCommentsByMovieId('noSuchId')

        then:
        actualComments.isEmpty()
    }

    @Unroll
    def "should return comments for a movie #movieId"() {
        given:
        def commentsRepo = new CommentsStaticRepository()

        when:
        def actualComments = commentsRepo.getCommentsByMovieId(movieId)

        then:
        actualComments.containsAll(comments)
        actualComments.size() == comments.size()

        where:
        movieId | comments
        '1'     | [new CommentDetails(movieId: '1', userName: 'user1', message: 'Great movie1!'),
                   new CommentDetails(movieId: '1', userName: 'hater', message: 'This isn\'t great :('),
                   new CommentDetails(movieId: '1', userName: 'spammer', message: 'Buy this big toy!'),
                   new CommentDetails(movieId: '1', userName: 'hater', message: 'Oh, this spammers :(')]
        '2'     | [new CommentDetails(movieId: '2', userName: 'user1', message: 'Great movie2!'),
                   new CommentDetails(movieId: '2', userName: 'hater', message: 'This is also boring'),
                   new CommentDetails(movieId: '2', userName: 'spammer', message: 'Buy this big toy!')]
    }
}
