package com.test.api.marvel_challenge.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GenericBeansInjector {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
