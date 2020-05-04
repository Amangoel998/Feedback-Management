package com.cg.feedback;

import com.cg.feedback.exceptions.CustomException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFeedbackApplication {
    public static void main(String... args) throws CustomException {
        SpringApplication.run(SpringFeedbackApplication.class, args);
    }
}