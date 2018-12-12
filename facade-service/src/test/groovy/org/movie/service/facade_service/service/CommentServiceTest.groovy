package org.movie.service.facade_service.service

import org.movie.service.facade_service.exception.InternalServiceUnavailableException
import org.movie.service.facade_service.model.CommentDetails
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestOperations
import spock.lang.Specification

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */

class CommentServiceTest extends Specification {

    def "should throw IllegalArgumentException on null movie id"() {
        when:
        new CommentsService(Mock(RestOperations)).getComments(null)

        then:
        thrown IllegalArgumentException
    }

    def "should return comments"() {
        given:
        def restTemplate = Mock(RestOperations)
        def commentsService = new CommentsService(restTemplate)
        def movieId = '1'

        when:
        def actualComments = commentsService.getComments(movieId)

        then:
        1 * restTemplate.exchange(commentsService.commentsServiceCommentsPath + movieId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentDetails>>() {
        }) >> new ResponseEntity<List<CommentDetails>>([new CommentDetails(userName: 'user1', message: 'msg1'),
                                                        new CommentDetails(userName: 'user2', message: 'msg2')],
                HttpStatus.OK)
        actualComments.size() == 2
        actualComments.containsAll([new CommentDetails(userName: 'user1', message: 'msg1'),
                                    new CommentDetails(userName: 'user2', message: 'msg2')])
    }

    def "should throw InternalServiceUnavailableException when comments service unavailable"() {
        given:
        def restTemplate = Mock(RestOperations)
        def commentsService = new CommentsService(restTemplate)
        def movieId = '1'

        when:
        commentsService.getComments(movieId)

        then:
        1 * restTemplate.exchange(commentsService.commentsServiceCommentsPath + movieId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CommentDetails>>() {
        }) >> { throw new ResourceAccessException('smth went wrong!') }
        thrown InternalServiceUnavailableException
    }
}
