package com.ayurveda.questionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableMongoAuditing
@CrossOrigin(origins = "*")
public class QuestionnaireBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireBackendApplication.class, args);
        System.out.println("🌿 Questionnaire Backend Running: http://localhost:8080/api");
    }
}
