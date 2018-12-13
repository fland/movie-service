package org.movie.service.facade_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

/**
 * @author Maksym Bondarenko
 * @version 1.0 12.12.18
 */
@Configuration
public class Config {

    @Bean
    @Scope(SCOPE_SINGLETON)
    RestOperations getRestTemplate() {
        return new RestTemplate();
    }

}
