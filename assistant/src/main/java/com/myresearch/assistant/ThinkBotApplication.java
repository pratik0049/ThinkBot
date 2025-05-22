package com.myresearch.assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class ThinkBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThinkBotApplication.class, args);
    }
}
