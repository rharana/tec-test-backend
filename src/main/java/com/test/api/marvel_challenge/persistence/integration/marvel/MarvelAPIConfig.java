package com.test.api.marvel_challenge.persistence.integration.marvel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MarvelAPIConfig {
    private long timestamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;
}
