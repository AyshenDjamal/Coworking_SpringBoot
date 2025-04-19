
package com.example.springapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

       // ./mvnw clean package                    jar fayili alir
        //docker build -t my-spring-api .        Docker image yaradir
        //docker run -d -p 8080:8080 --name spring-api-container my-spring-api           Yaranan docker image run edir


    @GetMapping("/api/salam-dunya")
    public String hello() {
        return "Salam dunya yeni endpoint";
    }

    // Yeni JSON qaytaran endpoint
    @GetMapping("/api/hello-json")
    public MyResponse helloJson() {
        return new MyResponse("Salam dunya", "Bu, JSON formatında cavabdır.");
    }

    // JSON obyekti olaraq qaytarılacaq model sinifi
    public static class MyResponse {
        private String message;
        private String description;

        // Konstruktor
        public MyResponse(String message, String description) {
            this.message = message;
            this.description = description;
        }

        // Getter və Setter metodları
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

