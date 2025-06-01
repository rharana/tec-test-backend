package com.test.api.marvel_challenge.persistence.integration.marvel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelAPIConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;
    private long timestamp = new Date(System.currentTimeMillis()).getTime();

    public long getTimestamp() {
        return timestamp;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;

    private String getHash(){
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return md5Encoder.encode(hashDecoded);
    }

    public Map<String, String> getAuthenticationQueryParams(){
        Map<String, String> securityQueryParams = new HashMap<>();
        securityQueryParams.put("ts", Long.toString(this.getTimestamp()));
        securityQueryParams.put("apikey", this.getPublicKey());
        securityQueryParams.put("hash", this.getHash());
        return securityQueryParams;
    }
}
