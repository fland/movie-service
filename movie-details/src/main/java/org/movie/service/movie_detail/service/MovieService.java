package org.movie.service.movie_detail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maksym Bondarenko
 * @version 1.0 10.12.18
 */

@RestController
@Slf4j
public class MovieService {

    @RequestMapping(value = "/movie/{uuid}")
    public String get(@PathVariable String uuid) {
        log.info("Movie uuid: {}", uuid);
        return "{}";
    }
}
