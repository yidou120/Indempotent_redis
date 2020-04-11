package com.edou.indempotent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IndempotentRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndempotentRedisApplication.class, args);
    }

}
