package com.srgplv.s3metrics.client;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.AWSRequestMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class S3Client {
    private static final boolean PATH_STYLE_ACCESS_ENABLED = true;

    @Autowired
    private ClientProperties clientProperties;

    RequestMetricCollector requestMetricCollector = new RequestMetricCollector() {
        @Override
        public void collectMetrics(Request<?> request, Response<?> response) {
            int poolCounter = request.getAWSRequestMetrics().getTimingInfo().getCounter(AWSRequestMetrics.Field.HttpClientPoolLeasedCount.name()).intValue();
            log.info("Leased connections: {}", poolCounter);
        }
    };

    @Bean
    public AmazonS3 getClient() {
        ClientConfiguration clientConfiguration = new ClientConfiguration()
                .withProtocol(clientProperties.getProtocol());
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(clientProperties.getAccessKey(), clientProperties.getSecretKey())))
                .withClientConfiguration(clientConfiguration)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(clientProperties.getEndpoint(), clientProperties.getRegion())
                )
                .withPathStyleAccessEnabled(PATH_STYLE_ACCESS_ENABLED)
                .withMetricsCollector(requestMetricCollector)
                .build();
    }
}
