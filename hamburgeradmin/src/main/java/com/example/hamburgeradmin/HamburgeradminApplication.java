package com.example.hamburgeradmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class HamburgeradminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HamburgeradminApplication.class, args);
    }

}
