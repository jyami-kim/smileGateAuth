package com.ex.smile_authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SmileAuthserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmileAuthserverApplication.class, args);
    }

}

