package com.srgplv.s3metrics.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services {
    @Autowired
    AmazonS3 client;

    public S3Object getS3Object(String bucketName, String keyName){
        return client.getObject(new GetObjectRequest(bucketName, keyName));
    }
}
