package com.srgplv.s3metrics.client;

import com.amazonaws.Protocol;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("s3client")
public class ClientProperties {
    public ClientProperties() {}

    private final static String DEFAULT_REGION = "us-east-1";
    private final static Protocol DEFAULT_PROTOCOL = Protocol.HTTP;

    private String endpoint;

    private String region = DEFAULT_REGION;

    private String secretKey;

    private String accessKey;

    private Protocol protocol = DEFAULT_PROTOCOL;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

  }
