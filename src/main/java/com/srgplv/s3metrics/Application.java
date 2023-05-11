package com.srgplv.s3metrics;

import com.srgplv.s3metrics.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@SpringBootApplication
public class Application {
    @Autowired
    Services services;

    @RequestMapping(value = {"", "/"})
    public void index() throws IOException {
        System.out.println(services.getS3Object("mybucket", "test.zip").getObjectContent().available());
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
