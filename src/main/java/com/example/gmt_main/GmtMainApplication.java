package com.example.gmt_main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class GmtMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmtMainApplication.class, args);
    }
}